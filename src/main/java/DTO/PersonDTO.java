/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;


/**
 *
 * @author benja
 */

public class PersonDTO implements Serializable {

   private String name;
   private String height;
   private String mass;
   private String hair_color;
   private String skin_color;
   private String eye_color;
   private String birth_year;
   private String gender;
   private String url;

    public PersonDTO(String name, String height, String mass, String hair_color, String skin_color, String eye_color, String birth_year, String gender, String url) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hair_color = hair_color;
        this.skin_color = skin_color;
        this.eye_color = eye_color;
        this.birth_year = birth_year;
        this.gender = gender;
        this.url = url;
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "name=" + name + ", height=" + height + ", mass=" + mass + ", hair_color=" + hair_color + ", skin_color=" + skin_color + ", eye_color=" + eye_color + ", birth_year=" + birth_year + ", gender=" + gender + ", url=" + url + '}';
    }
   
   
 
  

 

   

}
