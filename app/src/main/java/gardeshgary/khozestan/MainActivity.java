package gardeshgary.khozestan;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Locale;

import gardeshgary.khozestan.adapters.DrawerAdapter;
import gardeshgary.khozestan.view.fragments.AboutFragment;
import gardeshgary.khozestan.view.fragments.ContactUsFragment;
import gardeshgary.khozestan.view.fragments.GardeshgaryFragment;
import gardeshgary.khozestan.view.fragments.NewsDetailFragment;
import gardeshgary.khozestan.view.fragments.NewsFragment;

public class MainActivity extends AppCompatActivity {
	private DrawerLayout drawerLayout;
	private DrawerAdapter adapter;
	private int menuPosition = 0; // it should be zero otherwise #selectItem won't be called

	private Class<?>[] fragments = {
			null,
			NewsFragment.class,
			GardeshgaryFragment.class,
			ContactUsFragment.class,
			AboutFragment.class,
			NewsDetailFragment.class
	};

	private static final int NEWS = 1;
	public static final int NEWS_DETAIL = 7;
	private static final int GARDESHGARY = 2;
	private static final int CONTACT_US = 3;
	private static final int ABOUT = 4;
	private static final int EXIT = 6;

	// Default selected fragment
	private static final int DEFAULT = NEWS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		changeAppLanguage(this);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window w = getWindow();
			w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		} else {
			toolbar.setPadding(0, 0, 0, 0);
		}

		RecyclerView navigation = (RecyclerView) findViewById(R.id.navigation_view);
		navigation.setHasFixedSize(true);
		adapter = new DrawerAdapter(this);
		navigation.setAdapter(adapter);

		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		navigation.setLayoutManager(layoutManager);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		final View appMainView = findViewById(R.id.app_main_layout);
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
			int slidingDirection = +1;

			{
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
					if (isRTL())
						slidingDirection = -1;
				}
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					slidingAnimation(drawerView, slideOffset);
				}
			}

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			private void slidingAnimation(View drawerView, float slideOffset) {
				appMainView.setTranslationX(slideOffset * drawerView.getWidth() * slidingDirection);
				drawerLayout.bringChildToFront(drawerView);
				drawerLayout.requestLayout();
			}
		};

		drawerLayout.addDrawerListener(drawerToggle);
		drawerToggle.syncState();

		selectItem(DEFAULT, null);
	}

	@Override
	public void onBackPressed() {
		if (menuPosition != DEFAULT) {
			selectItem(DEFAULT, null);
		} else {
			finish();
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	private boolean isRTL() {
		return getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Checking for the "menu" key
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
				drawerLayout.closeDrawers();
			} else {
				drawerLayout.openDrawer(GravityCompat.START);
			}
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	public void selectItem(int item, Fragment fragment) {
		if (item == EXIT) {
			finish();
			return;
		}

//		beforeMenuChange(item);
		if (item != NEWS_DETAIL) {
			if (menuPosition != item) {
				try {
					getSupportFragmentManager()
							.beginTransaction()
							.replace(
									R.id.fragment_holder,
									(Fragment) fragments[item].newInstance(),
									fragments[item].getName()
							).addToBackStack(null).commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
				menuPosition = item;
			}
		} else {
			menuPosition = item;
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment, "").addToBackStack(null).commit();
		}
		adapter.setSelectedItem(item);

		drawerLayout.closeDrawers();
	}

	public void onClickItem(int position) {
		selectItem(position, null);
	}

	public void changeAppLanguage(Context context) {
		String localeCode = "fa".replaceAll("-(IR|AF)", "");
		Locale locale = new Locale(localeCode);
		Locale.setDefault(locale);
		Resources resources = context.getResources();
		Configuration config = resources.getConfiguration();
		config.locale = locale;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			config.setLayoutDirection(config.locale);
		}
		resources.updateConfiguration(config, resources.getDisplayMetrics());
	}
}
