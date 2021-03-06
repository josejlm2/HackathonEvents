package com.dreamtheimpossiblestudios.hackathonevents;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by Jose on 2/27/2015.
 */


@ParseClassName("Events")
public class Hackathon extends ParseObject{
    private ParseFile picture;

    public Hackathon(){

    }

    public String getName(){
        return getString("Name");
    }

    public void setName(String Name){
        put("Name", Name);
    }

    public String getLocation(){
        return getString("Location");
    }

    public void setLocation(String Location){
        put("Location", Location);
    }

    public void setLogo(ParseFile Logo){
        picture = Logo;
        put("Logo", Logo);
    }

    public ParseFile getLogo(){
        return getParseFile("Logo");
    }

}
