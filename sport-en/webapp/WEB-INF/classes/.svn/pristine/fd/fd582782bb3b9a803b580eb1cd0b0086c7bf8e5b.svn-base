package com.appscomm.sport.model;
import java.io.Serializable;
import java.sql.Timestamp;
 public  class RemaindVO implements Serializable{
  /*
   
   remindId:提醒ID
repeat:频率(
0000000:永不,
1100001:周一,周二,周日
)
time:时间
type:提醒类型 (
1111111:自定义
0000000:吃饭
0000001:吃药
0000010:动一动
0000011:睡觉
0000100:喝水
0000101:距离提醒
0000110:时间提醒
0000111:运动提醒
)
text:提醒事项
detail:详细描述
status:提醒状态(
0:关闭,
1:开启
)
doType:操作状态(
0:删除
1:添加
2:更新
)

   
   * */
      private static final long serialVersionUID = -201310151735371332L;
      private long	id;
      private String userId;
      private String watchId;
      private String repeat;
      private String time;
      private String type;
      private String text;
      private String detail;
      private int status;
      private int doType;
      private Timestamp upload_time;
      
      public Timestamp getUpload_time(){
    	  return this.upload_time;
      }
      
      public void setUpload_time(Timestamp upload_time){
    	  this.upload_time = upload_time;
      }
      
      public String getWatchId(){
         return this.watchId;
      }
      public void setWatchId(String watchId){
         this.watchId=watchId;
      }
      public String getRepeat(){
         return this.repeat;
      }
      public void setRepeat(String repeat){
         this.repeat=repeat;
      }
      public String getTime(){
         return this.time;
      }
      public void setTime(String time){
         this.time=time;
      }
      public String getType(){
         return this.type;
      }
      public void setType(String type){
         this.type=type;
      }
      public String getText(){
         return this.text;
      }
      public void setText(String text){
         this.text=text;
      }
      public String getDetail(){
         return this.detail;
      }
      public void setDetail(String detail){
         this.detail=detail;
      }
      public int getStatus(){
         return this.status;
      }
      public void setStatus(int status){
         this.status=status;
      }
	public int getDoType() {
		return doType;
	}
	public void setDoType(int doType) {
		this.doType = doType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
   }

