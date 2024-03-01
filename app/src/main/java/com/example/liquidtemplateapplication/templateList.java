package com.example.liquidtemplateapplication;

import java.util.ArrayList;

public class templateList {
    static ArrayList<String[]> listOfData= getList();

    static ArrayList<String[]> getList(){
        ArrayList<String[]> list = new ArrayList<>();
        list.add(new String[]{"4-May-19","01:39","","22.1","27.5","84.4","86.9","0.0",
                "0.026","121","9.7","12.7","1.3","After connecting I lead to motor"});
        list.add(new String[]{"4-May-19","06:04","","21.6","38.6","82.2","84.8","0.0",
                "0.013","121","9.8","10.2","1.3","Before Packer"});
        list.add(new String[]{"4-May-19","11:42","","25.7","70","85.2","87.9","0.0",
                "0.026","122","9.8","8.8","4.4","After Packer Penetrator"});

        list.add(new String[]{"4-May-19","14:32","10","29.2","101.1","89.9","93.6","0.0",
                "0.014","122","9.8","9.09","4.5","RIH 4 1/2'' Tubing"});
        list.add(new String[]{"4-May-19","17:03","35","380.1","427","94","97.5","0.0",
                "0.014","122","9.8","6.86","4.4","RIH 4 1/2'' Tubing"});
        list.add(new String[]{"4-May-19","18:41","58","576","567","96.1","99.9","0.0",
                "0.014","121","9.8","4.31","4.4","RIH 4 1/2'' Tubing"});
        list.add(new String[]{"4-May-19","22:37","95","975.9","1012.3","102.8","107","0.012",
                "0.018","121","9.7","4.45","4.44","RIH 4 1/2'' Tubing"});
        list.add(new String[]{"5-May-19","1:15","120","1370.2","1369.1","109","114.3","0.016",
                "0.01","121","9.8","3.95","4.5","RIH 4 1/2'' Tubing"});
        list.add(new String[]{"5-May-19","3:15","134","1601","1591","113.7","118.4","0.016",
                "0.014","121","9.8","2.95","4.52","RIH 4 1/2'' Tubing"});


        list.add(new String[]{"5-May-19","8:30","","1691.2","1682","118.1","122.6","0.016",
                "0.009","121","9.8","2.05","4.53","on the hunger"});
        list.add(new String[]{"5-May-19","13:00","","1692","1681","118","123","0.016",
                "0.009","121","9.8","3.06","4.33","After RMS on hunger"});
        list.add(new String[]{"6-May-19","1:30","","1695","1789","126","131","0.019",
                "0.021","121","9.8","1.28","4.43","After Bonnet"});


        return list;
    }
}


