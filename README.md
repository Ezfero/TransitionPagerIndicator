# TransitionPagerIndicator

A simple library that provides ViewPager indication with nice and smooth animations.

![preview](http://i.imgur.com/DUPsdgq.gif)

To add library to your project, paste the next lines to your upper-level build.gradle, inside ```repositories``` block:<br/>
```
maven {
	jcenter()
}
```
	
In your app-level build.gradle add this line inside ```dependencies``` block:<br/>
```
compile 'com.silgrid:indicator:0.1.2'
```
	
	
Inside your xml layout file, add<br/>
```
<com.silgrid.indicator.TransitionPagerIndicator
		android:id="@+id/indicator"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_gravity="center|bottom"
		android:layout_marginBottom="50dp"
		app:indicatorColor="#000000"
		app:indicatorSize="10dp"
		app:indicatorStyle="square"
		/>
```

And in java code initialize it like:<br/>

```
TransitionPagerIndicator indicator = (TransitionPagerIndicator) findViewById(R.id.indicator);
indicator.setViewPager(pager);
```

You can customize indicator with these properties from xml:

```
indicatorColor - set color of the indicator
indicatorSize - set size of the indicator
indicatorStyle - set style of indicator. Could be one of {circle, square, diamond}
```
