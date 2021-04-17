package com.example.movieservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String title;
    private String director;
    private String actor;
    private int released;
    private String genre;
    private String language;

    public Integer movieTicketPrice(int numberOfPersons)
    {
        int ticketPrice = 0;
       
        if(released <= 1950){
            ticketPrice = 100;
        }
        else if(released > 1950 & released <= 2000){
            ticketPrice = 200;
        }
        else if(released > 2000 & released <= 2020){
            ticketPrice = 250;
        }
        else{
            ticketPrice = 300;
        }
        return ticketPrice * numberOfPersons;            
    }
    
    public Integer getId() {
        return id;
      }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String s)
    {
        title  = s;
    }
    public String getTitle()
    {
        return title;
    }

    public void setDirector(String s)
    {
        director  = s;
    }
    public String getDirector()
    {
        return director;
    }

    public void setActor(String s)
    {
        actor  = s;
    }
    public String getActor()
    {
        return actor;
    }

    public void setGenre(String s)
    {
        genre  = s;
    }
    public String getGenre()
    {
        return genre;
    }

    public Integer getReleased() {
        return released;
      }
    
    public void setReleased(Integer year) {
        released = year;
    }
    

    public void setLanguage(String s)
    {
        language  = s;
    }
    public String getLanguage()
    {
        return language;
    }


    
}
