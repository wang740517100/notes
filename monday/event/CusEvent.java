package cn.wangkf.monday.event;

import java.util.EventObject;

/**
 * 事件
 */
public class CusEvent extends EventObject {
    private static final long serialVersionUID = 1L;  


    private Object source;//事件源
      
    public CusEvent(Object source){
        super(source);  
        this.source = source;  
    }  
  
    public Object getSource() {  
        return source;  
    }  
  
    public void setSource(Object source) {  
        this.source = source;  
    }  
}  