# BaseHomePager-master
---
开源地址：[https://github.com/LingChenJie/BaseHomepager-master](https://github.com/LingChenJie/BaseHomepager-master)

这是一个封装了android常用的底部导航栏+fragment的库，用这个库，我们可以几行代码就搞定大多数APP的主界面的布局

<img src="/screen/abc.gif" alt="screenshot" title="主界面" width="270" height="486" />

## 使用步骤


### 1. 在project的build.gradle添加如下代码(如下图)

	 ...
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
    }
    ...
![](http://upload-images.jianshu.io/upload_images/4037105-2faa5daca3bfe8a0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Sync项目以后，引入这个库就成功了~~~
### 2. 在Module的build.gradle添加依赖

   	dependencies {
	    ...
	    compile 'com.github.LingChenJie:BaseHomepager-master:1.0.0'
	}
    
### 3. 在xml中,加入以下代码:

	  <com.jiechen.tabview.TabView
	        android:id="@+id/tabView"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent">
	  </com.jiechen.tabview.TabView>


### 4. 在项目当中添加如下代码:

        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.drawable.tab01_sel,R.drawable.tab01_unsel,"首页",  FragmentCommon.newInstance());
        TabViewChild tabViewChild02=new TabViewChild(R.drawable.tab02_sel,R.drawable.tab02_unsel,"分类",  FragmentCommon.newInstance());
        TabViewChild tabViewChild03=new TabViewChild(R.drawable.tab03_sel,R.drawable.tab03_unsel,"资讯",  FragmentCommon.newInstance());
        TabViewChild tabViewChild04=new TabViewChild(R.drawable.tab04_sel,R.drawable.tab04_unsel,"购物车",FragmentCommon.newInstance());
        TabViewChild tabViewChild05=new TabViewChild(R.drawable.tab05_sel,R.drawable.tab05_unsel,"我的",  FragmentCommon.newInstance());
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild04);
        tabViewChildList.add(tabViewChild05);

> 是不是看起来感觉好复杂？其实就是new了5个实体类，把这5个实体类添加到了一个集合里，我们最终想要的是tabViewChildList这个集合,每个实体类的构造方法有四个参数:

 * 第一个参数：导航栏上面的某一个tab被点击时候，相应的切换的图片
 * 第二个参数：导航栏上面的tab没有被被点击时候，相应的切换的图片
 * 第三个参数：导航栏上面的某一个tab的文字显示
 * 第四个参数：导航栏上面的某一个tab对应的Fragment对象，传进来就可以


### 5 为TabView设置数据源:

    TabView tabView= (TabView) findViewById(R.id.tabView);
    tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
    tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
                 Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
            }
        });

---

当然，还能自定义很多属性，参见：[http://www.cniao5.com/forum/thread/8696c118194d11e794ea00163e0230fa](http://www.cniao5.com/forum/thread/8696c118194d11e794ea00163e0230fa)

