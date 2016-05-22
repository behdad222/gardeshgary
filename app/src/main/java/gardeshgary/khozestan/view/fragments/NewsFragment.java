package gardeshgary.khozestan.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gardeshgary.khozestan.R;
import gardeshgary.khozestan.adapters.NewsAdapter;

public class NewsFragment extends Fragment {

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_news, container, false);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		NewsAdapter adapter = new NewsAdapter();
		recyclerView.setAdapter(adapter);

		return view;
	}
}
