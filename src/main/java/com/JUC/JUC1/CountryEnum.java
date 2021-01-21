package com.JUC.JUC1;


import lombok.Getter;

/*
   枚举类：相当于一个数据库  CountryEnum，为数据库,ONE(1,"齐") ONE 为表 ，里面的为字段
 */
public enum CountryEnum {

    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"韩"),FOUR(4,"魏"),FIVE(5,"赵"),SIX(6,"燕");
    @Getter private Integer sum;
    @Getter private  String name;


    CountryEnum(Integer sum, String name) {
        this.sum = sum;
        this.name = name;
    }
public  static  CountryEnum  add(int index){
    CountryEnum[] myArray = CountryEnum.values();
    for (CountryEnum element: myArray
         ) {
        if (index==element.getSum()){
            return  element;
        }

    }


    return  null;
}

}
