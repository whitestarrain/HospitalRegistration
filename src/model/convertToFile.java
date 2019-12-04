package model;

//！本类仅仅用来生成序列化对象，并非主要部分，并不需要阅览

/* 
       题目要求是从json或序列化对象中读取。我在这里选择序列化对象
       在一开始初始化时，先从txt文件中读取，构造对象后再序列化对象 
       构造对象时将对象节点存储在map中，也就是把树结构存储在map中（遍历时并不会使用HashMap的get()函数）
*/
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class convertToFile {
    public static void convert() {

        HashMap<String, Patient> patient = new HashMap<String, Patient>();
        BufferedReader bufread = null;
        String s = null;
        String[] arrtemp = null;
        try {
            bufread = new BufferedReader(new FileReader("source/patient.txt"));
            while ((s = bufread.readLine()) != null) {
                arrtemp = s.split(" ");
                patient.put(arrtemp[0], new Patient(arrtemp[0], arrtemp[1]));
            }
        } catch (IOException e) {
            System.out.println("输入流打开失败");
            e.printStackTrace();
        } finally {
            try {
                bufread.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(patient);

        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        try {
            bufread = new BufferedReader(new FileReader("source/doctor.txt"));
            while ((s = bufread.readLine()) != null) {
                arrtemp = s.split(" ");
                doctors.add(new Doctor(arrtemp[0], arrtemp[1]));
            }
        } catch (IOException e) {
            System.out.println("输入流打开失败");
            e.printStackTrace();
        } finally {
            try {
                bufread.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(doctors);

        ArrayList<Medicine> medicines = new ArrayList<Medicine>();
        try {
            bufread = new BufferedReader(new FileReader("source/medicine.txt"));
            while ((s = bufread.readLine()) != null) {
                arrtemp = s.split(" ");
                medicines.add(new Medicine(arrtemp[0], arrtemp[1], Integer.valueOf(arrtemp[2]),
                        Float.valueOf(arrtemp[3]), arrtemp[4]));
            }
        } catch (IOException e) {
            System.out.println("输入流打开失败");
            e.printStackTrace();
        } finally {
            try {
                bufread.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(medicines);

        HashMap<String, DiseaseType> alldisease = new HashMap<String, DiseaseType>();
        DiseaseType dtemp = null;// 临时存储病种变量
        DiseaseType root = null;
        // 初始化temp
        try {
            bufread = new BufferedReader(new FileReader("source/Disease.txt"));// 在读第一行使初始化root，因此额外提出来写
            s = bufread.readLine();
            arrtemp = s.split(" ");
            dtemp = new DiseaseType(arrtemp[0], arrtemp[1], arrtemp[2]);
            alldisease.put(arrtemp[0], dtemp);
            root = dtemp;
            while ((s = bufread.readLine()) != null) {// 疾病信息初始化
                arrtemp = s.split(" ");
                dtemp = new DiseaseType(arrtemp[0], arrtemp[1], arrtemp[2]);
                alldisease.put(arrtemp[0], dtemp);
            }
            bufread.close();// FIXME 为何reset会报错？？
            bufread = new BufferedReader(new FileReader("source/Disease.txt"));
            while ((s = bufread.readLine()) != null) {// 添加疾病的子病种以及病人中的一方
                arrtemp = s.split(" ");
                for (int i = 3; i < arrtemp.length; i++) {
                    dtemp = alldisease.get(arrtemp[0]);
                    if (Integer.valueOf(arrtemp[i]) / 100 == 2) {
                        if (dtemp.subDiseaseTypes == null)
                            dtemp.subDiseaseTypes = new ArrayList<DiseaseType>();
                        dtemp.subDiseaseTypes.add(alldisease.get(arrtemp[i]));
                    } else if (Integer.valueOf(arrtemp[i]) / 100 == 1) {
                        if (dtemp.patient == null) {
                            dtemp.patient = new ArrayList<String>();
                        }
                        dtemp.patient.add(arrtemp[i]);
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("病种文件读取失败");
            e.printStackTrace();
        } finally {
            try {
                bufread.close();
            } catch (Exception e) {
                System.out.println("流关闭失败");
            }
        }
        System.out.println(alldisease);

        ArrayList<Records> records = new ArrayList<Records>();
        try {
            bufread = new BufferedReader(new FileReader("source/records.txt"));
            while ((s = bufread.readLine()) != null) {
                arrtemp = s.split(" ");
                records.add(new Records(arrtemp[0], arrtemp[1], arrtemp[2], arrtemp[3], arrtemp[4]));
            }
        } catch (IOException e) {
            System.out.println("输入流打开失败");
            e.printStackTrace();
        } finally {
            try {
                bufread.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(records);
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/patients.HashMap"));
            out.writeObject(patient);
            out.close();
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/medicines.ArrayList"));
            out.writeObject(medicines);
            out.close();
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/doctors.ArrayList"));
            out.writeObject(doctors);
            out.close();
            // 有root就够了，不需要这个HashMap实例化
            // out = new ObjectOutputStream(new
            // FileOutputStream("objectfiles/diseases.HashMap"));
            // out.writeObject(alldisease);
            // out.close();
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/records.ArrayList"));
            out.writeObject(records);
            out.close();
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/root.DiseaseType"));
            out.writeObject(root);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        convert();
    }
}