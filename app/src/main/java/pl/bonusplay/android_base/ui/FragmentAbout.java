package pl.bonusplay.android_base.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pl.bonusplay.android_base.R;

public class FragmentAbout extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_about, parent, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		// Setup handles to view objects
	}
}