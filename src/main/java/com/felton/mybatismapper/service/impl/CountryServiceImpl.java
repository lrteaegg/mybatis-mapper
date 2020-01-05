package com.felton.mybatismapper.service.impl;

import com.felton.mybatismapper.dao.ICountryDao;
import com.felton.mybatismapper.dao.IPictureDao;
import com.felton.mybatismapper.domain.entity.Country;
import com.felton.mybatismapper.entity.Picture;
import com.felton.mybatismapper.service.ICountryService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * CountryServiceImpl
 *
 * @author liurui
 * @Description:
 * @date 2019/12/20
 */
@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private ICountryDao countryDao;
    @Autowired
    private IPictureDao pictureDao;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Value("${global.pircture-dir}")
    private String picturePath;


    @Override
    public int delectByPrimaryKey(Integer id) {
        int row = countryDao.deleteByPrimaryKey(id);
        return row;
    }

    @Override
    public int addCountry(Country country) {
        return 0;
    }

    @Override
    public int addLocalCountry() {
        ForkJoinPool pool = new ForkJoinPool();
        long startTime = System.currentTimeMillis();
//        int countSize = 0;
        Map<String, Integer> importMap = new Hashtable<>();
        System.out.println("开始导入");
//        String encoding = "GBK";
        List pictureList = new ArrayList<>();
        // 获取指定文件夹下所有的文件
        File[] files = new File(picturePath).listFiles();
        System.out.println("文件数量：" + files.length);
        List result = new ArrayList();

        long startTime1 = System.currentTimeMillis();
        for (File file: files) {
            if (file.isFile() && file.exists()) {
                if (file.getName().contains("jpg") || file.getName().contains("png")) {
                    importFile(file, pictureList);
                } else {
                    result.add(file.getName());
                }
            } else {
                return 0;
            }
        }
       /* SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        IPictureDao dao = sqlSession.getMapper(IPictureDao.class);
        System.out.println("读取文件消耗时间: " + (System.currentTimeMillis()-startTime1));
        List infoListTemp = pictureList.subList(0, pictureList.size()/4);
        List infoListTemp2 = pictureList.subList((pictureList.size()/4), (pictureList.size()/4)*2);
        List infoListTemp3 = pictureList.subList((pictureList.size()/4)*2, (pictureList.size()/4)*3);
        List infoListTemp4 = pictureList.subList((pictureList.size()/4)*3, pictureList.size());
        System.out.println("第一次插入："+ dao.insertList(infoListTemp)+"条");
        System.out.println("第二次插入："+ dao.insertList(infoListTemp2)+"条");
        System.out.println("第三次插入："+ dao.insertList(infoListTemp3)+"条");
        System.out.println("第四次插入："+ dao.insertList(infoListTemp4)+"条");*/
//        pictureDao.insertList(infoListTemp2);
        //--------------------------------------------------并发处理

        FileImportTask importTask = new FileImportTask(pictureList, 0,
                files.length-1, files.length/4);
        pool.invoke(importTask); // 同步调用
        importMap = importTask.join();
        for (Map.Entry e:importMap.entrySet()) {
            System.out.println("key: "+e.getKey()+", value: "+e.getValue());

        }
        System.out.println("消耗时间为：" + (System.currentTimeMillis() - startTime) + " ms");
        return 1;
    }

    public static void importFile(File file, List list) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] content = new byte[inputStream.available()];
            inputStream.read(content);
//            System.out.println("读取文件大小:" + content.length+", 文件名称为： "+ file.getName());
            Picture picture = new Picture();
            picture.setName(file.getName());
            picture.setContent(content);
            list.add(picture);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class FileImportTask extends RecursiveTask<Map<String, Integer>> {
        //        处理的数据分成10份
        private int THRESHOLD;
        private List<Picture> src; // 表示我们要实际统计的数组
        private int fromIndex; // 开始统计的标
        private int toIndex; // 统计到哪里结束的下标


        public FileImportTask(List src, int fromIndex, int toIndex, int THRESHOLD) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.THRESHOLD = THRESHOLD;
        }

        @Override
        protected Map<String, Integer> compute() {
//            Integer result = 0;
            Map<String, Integer> result = new Hashtable<>();
            if (toIndex - fromIndex < THRESHOLD) {
                /*SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
                IPictureDao dao = sqlSession.getMapper(IPictureDao.class);
                System.out.println(dao.getClass());*/
//                long start = System.currentTimeMillis();
                for (Picture p: src.subList(fromIndex, toIndex+1)) {
                    int id = pictureDao.insertSetBean(p);
                    result.put(p.getName().substring(0, p.getName().indexOf('.')), p.getId());
                }
//                result = pictureDao.insertList(src.subList(fromIndex, toIndex+1));
//                System.out.println("插入条数："+result.size()+", 消耗时间："+(System.currentTimeMillis()-start));
                return result;
            } else {
                int mid = (fromIndex + toIndex) /2 ;
                CountryServiceImpl.FileImportTask left = new CountryServiceImpl.FileImportTask(src, fromIndex, mid, THRESHOLD);
                CountryServiceImpl.FileImportTask right = new CountryServiceImpl.FileImportTask(src, mid + 1, toIndex, THRESHOLD);
                invokeAll(left,right);
                result.putAll(left.join());
                result.putAll(right.join());
                return  result;
            }
        }
    }
}
