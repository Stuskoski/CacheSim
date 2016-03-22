package com.company;

/**
 * Created by augustus on 3/21/16.
 * This is a memory object that will
 * contain all the variables for the
 * objects to be used in various functions
 */
public class memoryObj {
    public String address;
    public String hexAddress;
    public String tag;
    public String index;
    public String offset;
    public int blockNum;
    public String blockAddr;
    public boolean isHex;

    //Constructor
    public memoryObj(String addr, boolean isHexBool){
        address = addr;
        isHex = isHexBool;
    }

    public String calcTag(){
        int dec;
        int addZeros = 0;
        String hex;
        String bin;
        String tagLoc;
        String indexLoc;
        String offsetLoc;

        if(isHex){ //convert dec to hex if address is not in hex already
            hex = address;
            hexAddress = hex;
            dec = Integer.parseInt(hex, 16);
            bin = Integer.toBinaryString(dec);
        }else{
            dec = Integer.parseInt(address);
            hex = Integer.toHexString(dec);
            hexAddress = hex;
            bin = Integer.toBinaryString(dec);
        }

        if(bin.length() < Main.memAddrLength){
            addZeros = Main.memAddrLength - bin.length();
        }

        String oneZero = "0";
        String addingZeros = "";
        for(int i = 0; i<addZeros; i++){
            addingZeros += oneZero;
        }

        bin = addingZeros + bin;
        //System.out.println(bin);

        tagLoc = bin.substring(0, Main.tagSize-1);
        if(Main.index == 0){
            indexLoc = "";
        }else{
            indexLoc = bin.substring(Main.tagSize, Main.tagSize+Main.index-1);
        }
        offsetLoc = bin.substring(Main.tagSize+Main.index, bin.length()-1);

        String temp;
        temp = tagLoc+indexLoc;

        if(temp.equals("")){
            blockAddr = Integer.toHexString(0);
        }else{
            blockAddr = Integer.toHexString(Integer.parseInt(temp,2));
        }

        if(tagLoc.equals("")){
            tag = Integer.toHexString(0);
        }else{
            tag = Integer.toHexString(Integer.parseInt(tagLoc,2));
        }

        if(indexLoc.equals("")){
            index = Integer.toHexString(0);
        }else{
            index = Integer.toHexString(Integer.parseInt(indexLoc,2));
        }

        if(offsetLoc.equals("")){
            offset = Integer.toHexString(0);
        }else{
            offset = Integer.toHexString(Integer.parseInt(offsetLoc,2));
        }




        blockNum = Integer.parseInt(index, 16);

        return hex;
    }



}
