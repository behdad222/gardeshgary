package gardeshgary.khozestan.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gardeshgary.khozestan.MainActivity;
import gardeshgary.khozestan.NewsApi;
import gardeshgary.khozestan.R;
import gardeshgary.khozestan.adapters.NewsAdapter;
import gardeshgary.khozestan.model.Item;
import gardeshgary.khozestan.model.Items;
import gardeshgary.khozestan.service.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {
	private List<Item> items = new ArrayList<>();
	private NewsAdapter adapter;

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
		adapter = new NewsAdapter(this, items);
		recyclerView.setAdapter(adapter);

		getNotificationNumber();
		return view;
	}

	public void getNotificationNumber() {
		NewsApi newsApi = ServiceGenerator.createService(NewsApi.class);

		Call<Items> call = newsApi.getNews();

		call.enqueue(new Callback<Items>() {
			@Override
			public void onResponse(Call<Items> call, Response<Items> response) {
				if (response.isSuccessful()) {
					for (int i = 0; i < response.body().getItem().size(); i++) {
						items.add(response.body().getItem().get(i));
					}

					adapter.notifyDataSetChanged();
				}
			}

			@Override
			public void onFailure(Call<Items> call, Throwable t) {
				Toast.makeText(getContext(), "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void selectNews(int position) {
		NewsDetailFragment newsDetailFragment = new NewsDetailFragment();

		Bundle bundle = new Bundle();
		bundle.putSerializable("key", items.get(position));
		newsDetailFragment.setArguments(bundle);
		((MainActivity) getActivity()).selectItem(MainActivity.NEWS_DETAIL, newsDetailFragment);
	}
}
