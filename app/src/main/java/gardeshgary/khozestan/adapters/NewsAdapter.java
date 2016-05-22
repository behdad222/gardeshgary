package gardeshgary.khozestan.adapters;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gardeshgary.khozestan.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

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
        }

        @Override
        public void onClick(View view) {
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
		holder.title.setText("خبر شماره " + position);
		holder.description.setText("توضیح خبر شماره" + position);
		holder.date.setText("۱۳۹۴/۱/۱۲");
    }

    @Override
    public int getItemCount() {
        return 50;
    }
}
