package gardeshgary.khozestan.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import gardeshgary.khozestan.R;
import gardeshgary.khozestan.model.Item;

public class NewsDetailFragment extends Fragment {

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

		TextView title = (TextView) view.findViewById(R.id.title);
		TextView description = (TextView) view.findViewById(R.id.description);
		ImageView image = (ImageView) view.findViewById(R.id.image);

		Item item = (Item) getArguments().getSerializable("key");

		title.setText(item.getTitle());
		description.setText(item.getFull_description());
		title.setText(item.getTitle());

		if (!item.getImage().equals("")) {
			Picasso.with(getContext())
					.load(item.getImage())
					.into(image);
		}
		return view;
	}

}
