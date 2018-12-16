/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsu.mongo;

/**
 *
 * @author hossa
 */
public class SMS {
    public String _id;
    public String sender;
    public String date;
    public String length;
    public String isSpam;
    public String content;
    public String Delete ="Delete";
    public SMS()
    {
        
    }

    public String getDelete() {
        return Delete;
    }

    public void setDelete(String Delete) {
        this.Delete = Delete;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

  

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getIsSpam() {
        return isSpam;
    }

    public void setIsSpam(String isSpam) {
        this.isSpam = isSpam;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
