package pl.bonusplay.android_base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import pl.bonusplay.android_base.ui.FragmentAbout;
import pl.bonusplay.android_base.ui.FragmentHome;
import pl.bonusplay.android_base.ui.FragmentPreferences;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
	private Toolbar toolbar;
	private DrawerLayout drawer;
	private ActionBarDrawerToggle drawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerToggle = setupDrawerToggle();
		drawer.addDrawerListener(drawerToggle);

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		setupDrawerContent(navigationView);

		setFragment(FragmentHome.class);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}


	private ActionBarDrawerToggle setupDrawerToggle()
	{
		return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
	}

	private void setupDrawerContent(NavigationView navigationView)
	{
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item)
			{
				selectDrawerItem(item);
				return true;
			}
		});
	}

	private void selectDrawerItem(MenuItem item)
	{
		Class fragmentClass = null;

		switch(item.getItemId())
		{
			case R.id.nav_home:
				fragmentClass = FragmentHome.class;
				break;
			case R.id.nav_about:
				fragmentClass = FragmentAbout.class;
				break;
			case R.id.nav_settings:
				fragmentClass = FragmentPreferences.class;
				break;
			default:
				fragmentClass = FragmentHome.class;
				break;
		}

		setFragment(fragmentClass);

		item.setChecked(true);
		setTitle(item.getTitle());
		drawer.closeDrawers();
	}

	private void setFragment(Class fragmentClass)
	{
		Fragment fragment = null;

		try
		{
			fragment = (Fragment) fragmentClass.newInstance();
		}
		catch (IllegalAccessException | InstantiationException e)
		{
			e.printStackTrace();
		}

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item)
	{
		// action bar home/up action should open or close the drawer

		if (drawerToggle.onOptionsItemSelected(item))
			return true;

		switch(item.getItemId())
		{
			case R.id.nav_home:
				drawer.openDrawer(GravityCompat.START);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}
}