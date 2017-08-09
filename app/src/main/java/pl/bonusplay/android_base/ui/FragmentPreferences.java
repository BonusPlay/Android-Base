package pl.bonusplay.android_base.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import pl.bonusplay.android_base.R;

public class FragmentPreferences extends PreferenceFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}