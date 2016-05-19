package Kernel;

import java.sql.Timestamp;

public class Constants {
	//users type
	public static final int GUEST=0;
	public static final int SHOP=1;
	//guest gender
	public static final boolean MALE=false;
	public static final boolean FEMALE=true;
	

	public static class Order{
		private int o_id;
		private int g_id;
		private int s_id;
		private int i_id;
		private boolean isdone;
		private Timestamp timestamp;
		private int quant;
		
		public int getO_id() {
			return o_id;
		}
		public void setO_id(int o_id) {
			this.o_id = o_id;
		}
		public int getG_id() {
			return g_id;
		}
		public void setG_id(int g_id) {
			this.g_id = g_id;
		}
		public int getS_id() {
			return s_id;
		}
		public void setS_id(int s_id) {
			this.s_id = s_id;
		}
		public int getI_id() {
			return i_id;
		}
		public void setI_id(int i_id) {
			this.i_id = i_id;
		}
		public boolean isIsdone() {
			return isdone;
		}
		public void setIsdone(boolean isdone) {
			this.isdone = isdone;
		}
		public Timestamp getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
		}
		public int getQuant() {
			return quant;
		}
		public void setQuant(int quant) {
			this.quant = quant;
		}
		private void varinit(){
			i_id=-1;
			o_id=-1;
			g_id=-1;
			s_id=-1;
			quant=0;
			isdone=false;
			timestamp=new Timestamp(System.currentTimeMillis());
		}
		public Order(int oid,int gid,int sid,int iid,
				int quant,boolean isdone,Timestamp ts){
			o_id=oid;
			g_id=gid;
			s_id=sid;
			this.quant=quant;
			this.isdone=isdone;
			timestamp=ts;
		}
		public Order(){
			varinit();
		}
	}
	
	
	public static class Item{
		private int i_id;
		private int s_id;
		private String fullname;
		private int value;
		private String description;
		private boolean available;
		
		private void varinit(){
			i_id=-1;
			s_id=-1;
			value=0;
			fullname=null;
			description=null;
		}
		public Item(int iid,int sid,String name,int v,String des,boolean available){
			i_id=iid;
			s_id=sid;
			value=v;
			fullname=name;
			description=des;
			this.available=available;
		}
		public Item(){
			varinit();
		}
		public boolean isAvailable() {
			return available;
		}
		public int getI_id() {
			return i_id;
		}
		public void setI_id(int i_id) {
			this.i_id = i_id;
		}
		public int getS_id() {
			return s_id;
		}
		public void setS_id(int s_id) {
			this.s_id = s_id;
		}
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}
}
