package tw.org.iii.myapplication0325;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//需要使用多重繼承(NavigationView預設模板+ViewPager+TabLayout)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener,
        TabLayout.OnTabSelectedListener{

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private Fragment1 fragment1 = new Fragment1();
    private Fragment2 fragment2 = new Fragment2();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //***********以下是左側導航列(模板預設)**************************//

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //***********以下是中間標籤分頁**************************//

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //注册监听
        viewPager.addOnPageChangeListener(this);
        tabLayout.addOnTabSelectedListener(this);

        //添加适配器，在viewPager里引入Fragment
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return fragment1;
                    case 1:
                        return fragment2;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });//setAdapter()END


    }//onCreate()END

    // 自訂的ExpandListAdapter

    //***********以上是可展開式清單的**************************//

    //***********以下是中間標籤分頁的**************************//

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //TabLayout里的TabItem被选中的时候触发
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //viewPager滑动之后显示触发
        tabLayout.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //***********以上是中間標籤分頁的**************************//

    //***********以下是左邊導航列(模板預設)**************************//

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        //***********右上工具列程式碼寫入區**************************//

        if (id == R.id.action_search) {
            return true;

        }else if (id == R.id.action_news) {
            return true;

        }else if (id == R.id.action_settings) {

            Intent intent = new Intent();
            intent.setClass(MainActivity.this ,SystemSetting.class);
            startActivity(intent);

        }else if (id == R.id.action_about) {
            return true;

        }else if (id == R.id.action_exit) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected ()END

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myCard) {
            // Handle the camera action 左邊導覽列選項
            Intent intent = new Intent();
            intent.setClass(MainActivity.this , MyCard.class);
            startActivity(intent);

        } else if (id == R.id.nav_cardList) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this ,CardListGroup.class);
            startActivity(intent);

        } else if (id == R.id.nav_actList) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this ,ActListGroup.class);
            startActivity(intent);

        } else if (id == R.id.nav_actMap) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this ,ActMap.class);
            startActivity(intent);

        } else if (id == R.id.nav_qrCode) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this ,QrCode.class);
            startActivity(intent);

        } else if (id == R.id.nav_settings) {
            // Handle the camera action
            Intent intent = new Intent();
            intent.setClass(MainActivity.this ,SystemSetting.class);
            startActivity(intent);

        } else if (id == R.id.nav_aboutUs) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }//onNavigationItemSelected()END
}//calss MainActivity()End
