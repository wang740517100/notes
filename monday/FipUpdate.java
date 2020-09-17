package cn.wangkf.monday;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Created by stanley.wang on 2020/7/1.
 */
public class FipUpdate {

    public static void main(String[] args) throws Exception {
        StringBuilder tar = readTxt();
        boolean falg = false;
        int flagIndex = 0;
        boolean beginFalg = false;
        StringBuilder line = new StringBuilder();
        List<JSONObject> jsonList = Lists.newArrayList();
        for (int i = 0; i < tar.length(); i++) {
            String letter = tar.substring(i, i+1);
            if (!falg && "金".equals(letter) && flagIndex == 0) {
                flagIndex ++;
                continue;
            } else if (!falg && "融".equals(letter) && flagIndex == 1) {
                flagIndex ++;
                continue;
            } else if (!falg && "订".equals(letter) && flagIndex == 2) {
                flagIndex ++;
                continue;
            } else if (!falg && "单".equals(letter) && flagIndex == 3) {
                flagIndex ++;
                continue;
            } else if (!falg && "回".equals(letter) && flagIndex == 4) {
                flagIndex ++;
                continue;
            } else if (!falg && "收".equals(letter) && flagIndex == 5) {
                falg = true;
                flagIndex = 0;
                continue;
            }

            if (falg && !beginFalg && "{".equals(letter)) {
                beginFalg = true;
                line.append("{");
            } else if (falg && beginFalg && "}".equals(letter)) {
                line.append("}");
                JSONObject jsonObject = null;
                try {
                    jsonObject = JSON.parseObject(line.toString());
                } catch (Exception e) {
                    System.out.println(line.toString());
                    e.printStackTrace();
                    throw e;
                }

                jsonList.add(jsonObject);
                falg = false;
                beginFalg = false;
                line = new StringBuilder();
            } else if (falg && beginFalg) {
                line.append(letter);
            }
        }


        Set<String> codes = readLine();
        System.out.println(codes);
        System.out.println("---------------------------------------------------------");

//        StringBuilder resStr = new StringBuilder();
//        jsonList.forEach(j -> {
//            if (!codes.contains(j.getString("code"))) {
//
//
//                if (StringUtils.isBlank(j.getString("orderNode")) || StringUtils.isBlank(j.getString("orderNodeDetail")) ||
//                        StringUtils.isBlank(j.getString("id"))) {
//                    System.out.println(j);
//                }
//                resStr.append("update t8t_fi_fip.fip_order set order_node = " + j.getString("orderNode")
//                    + ", " + "order_node_detail = " + j.getString("orderNodeDetail") + " where id = "
//                        + j.getString("id") + ";");
//            }
//        });
//
//        System.out.println(jsonList.size());
//
//        writeTxt("e:\\20200701res.txt", resStr.toString());








    }


    public static void writeTxt(String txtPath, String content){
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        try {
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Set<String> readLine() throws IOException {
        File file = new File("e:\\今天修改的orderId.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Set<String> codes = Sets.newHashSet();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            codes.add(line.trim());
        }
        return codes;
    }


    public static StringBuilder readTxt() {
        String path = "e:\\t8t-fi-fip-sc-main.txt";
        File file = new File(path);
        StringBuilder str = new StringBuilder();
        try {
            BufferedReader rd = new BufferedReader(new FileReader(file));
            String s = rd.readLine();
            while (null != s) {
                str.append(s);
                s = rd.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
