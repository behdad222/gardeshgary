package gardeshgary.khozestan.adapters;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import gardeshgary.khozestan.R;
import gardeshgary.khozestan.model.Item;
import gardeshgary.khozestan.view.fragments.NewsFragment;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
	private List<Item> items;
	private NewsFragment newsFragment;

	public NewsAdapter(NewsFragment newsFragment, List<Item> items) {
		this.items = items;
		this.newsFragment = newsFragment;
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public AppCompatTextView title;
        public AppCompatTextView description;
        public AppCompatTextView date;
        public AppCompatImageView image;

        public ViewHolder(View view) {
            super(view);
			title = (AppCompatTextView) itemView.findViewById(R.id.title);
            description = (AppCompatTextView) itemView.findViewById(R.id.description);
            date = (AppCompatTextView) itemView.findViewById(R.id.date);
			image = (AppCompatImageView) itemView.findViewById(R.id.image);
			view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
			newsFragment.selectNews(getAdapterPosition());
        }
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
		holder.title.setText(items.get(position).getTitle());
		holder.description.setText(items.get(position).getDescription());
		holder.date.setText(items.get(position).getPubDate());

		if (!items.get(position).getImage().equals("")) {
			Picasso.with(newsFragment.getContext())
					.load(items.get(position).getImage())
					.into(holder.image);
		}
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
