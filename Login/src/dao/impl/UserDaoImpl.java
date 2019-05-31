/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.UserDao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import pojo.User;

/**
 *
 * @author Administrator
 */
public class UserDaoImpl implements UserDao {

    private static File file = new File("user.txt");

    static {
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public boolean login(String username, String password) {
        boolean flag=false;
        BufferedReader br=null;
        try{
               br=new BufferedReader(new FileReader(file));
               String line=null;
               while((line=br.readLine())!=null){
                   String[] datas=line.split("=");
                   if(datas[0].equals(username)&&datas[1].equals(password)){
                       flag=true;
                       break;
                   }
               }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(br!=null) {
		try {
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
            }
        }
           return flag;
     }

    @Override
    public void regist(User user) {
        BufferedWriter bw=null;
        try{
            bw=new BufferedWriter(new FileWriter(file,true));
            bw.write(user.getUsername()+"="+user.getPassword());
            bw.newLine();
            bw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(bw!=null) {
		try {
			bw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			}
		}
        }
    }

}
