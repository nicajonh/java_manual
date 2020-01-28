
package com.tida.manual.diymap;




import java.util.ArrayList;
import java.util.List;

/* 
    了解hashmap中entry实体的结构 
    crc16算法 
    hashmap底层=数组+链表 
    通过hash算法带来的好处， 快存快取  / 数组在存的时候是需要遍历的 
    HashMap底层是怎么回事? 
         
 */

public class DIYHaspMap<K,V> implements DIYMap<K,V> {
	 //定义默认数组大小
	 private int defaultLength=16;
	 //负载因子,扩容标准 useSize/数组长度>0.75扩容
	 private double defaultAddSizeFactor=0.75;
	 //使用数组位置的总数
	 private double useSize;//数组长度
	 
	 //定义Map骨架之一数组
	 private Entry<K,V>[] table;
	 
	 public DIYHaspMap(int defaultLength,double defaultAddSizeFactor){
		 if(defaultLength<0){
            throw new IllegalArgumentException("数组长度为负数"+defaultLength);
        }  
        if(defaultAddSizeFactor<=0 || Double.isNaN(defaultAddSizeFactor)){  
            throw new IllegalArgumentException("扩容标准必须大于0的数字"+defaultLength);
        }  
      
        this.defaultLength = defaultLength;
        this.defaultAddSizeFactor = defaultAddSizeFactor;  
          
        table=new Entry[defaultLength];
	 }
	 
	 //快速存取hash算法
	 public V put(K k,V v){
		 if(useSize>defaultAddSizeFactor*defaultLength){
			 //扩容
			 up2Size();
		 }
		 
		 //通过key来计算出存储的位置
		 int index=getIndex(k,table.length);
         Entry<K,V> entry=table[index];
         Entry<K,V> newEntry=new Entry<K,V>(k,v,null);
		 if(entry==null){
			 table[index]=newEntry;
			 useSize++;
		 }else{
			 //维护数组相同位置队列
			 Entry<K,V> tmp;
			 while((tmp=table[index])!=null){
				 tmp=tmp.next;
			 }
			 tmp.next=newEntry;
		 }
		 return newEntry.getValue();
	 }
	 
	private int getIndex(K k,int length){
	 //通常hashCode取mod法
		int m=length-1;
		int index=hash(k.hashCode())&m;
		return index>=0?index:-index;
	 
	}
    //创建自己的hash算法，保证计算出的位置 在数组中均匀分布  
    private int hash(int hashCode) {  
        hashCode=hashCode^((hashCode>>>20)^(hashCode>>>12));  
        return hashCode^((hashCode>>>7)^(hashCode>>>4));  
    } 

    //扩容数组
    private void up2Size(){
		Entry<K,V>[] newTable=new Entry[defaultLength*2];
	    //将原talbe中的entry重新,散列到新的table中
		againHash(newTable);
	}
	
	//将原table中的entry重新，散列到新的table中 
    private void againHash(Entry<K,V>[] newTable){
		//数组里面对象 封装到list中,包括同一位置 有列表结构的都解析出来  
		List<Entry<K,V>> entryList=new ArrayList<Entry<K,V>>();
		for(int i=0;i<table.length;i++){
			if(table[i]==null){
				continue;
			}
			findEntryByNext(table[i],entryList);
		}
		if(entryList.size()>0){
			useSize=0;
			defaultLength=defaultLength*2;
			table=newTable;
			for(Entry<K,V> entry:entryList){
				if (entry.next!=null){
					entry.next=null;
				}
				put(entry.getKey(),entry.getValue());
			}
		}
		
		
	}

    private void findEntryByNext(Entry<K,V> entry,List<Entry<K,V>> entryList){
		if(entry!=null && entry.next!=null){
			//这个entry对象已经形成链表结构
			entryList.add(entry);
			//递归链表
			findEntryByNext(entry.next,entryList);
		}else{
			entryList.add(entry);
		}
	}

	//快速
	@Override
	public V get(K k, V v){
		//通过key来计算出存储的位置
		int index=getIndex(k,table.length);
		Entry<K,V> entry=table[index];
		if(entry==null){
			throw new NullPointerException();
		}
		return findEntryByNext(k,entry);
	}

	@Override
	public V get(K v) {
		return null;
	}

	private V findEntryByNext(K k,Entry<K,V> entry){
		if(k==entry.getKey()||k.equals(entry.getKey())){
			return entry.v;
		}
		else if(entry.next!=null){
			return findEntryByNext(k,entry.next);
		}else {
			return null;
		}

	}
	
	class Entry<K,V> implements DIYMap.Entry<K,V>{
		K k;
		V v;
		Entry<K,V> next;
		public Entry(K k,V v,Entry<K,V> next){
			this.k = k;  
            this.v = v;  
            this.next = next; 
		}
		
	    @Override
		public K getKey(){
			return k;
		}
		
		@Override
		public V getValue(){
			return v;
		}
	}
 }
 
 