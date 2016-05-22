package gardeshgary.khozestan.adapters;


import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gardeshgary.khozestan.MainActivity;
import gardeshgary.khozestan.R;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
	private final MainActivity mainActivity;
	private static final int TYPE_HEADER = 0;
	private static final int TYPE_ITEM = 1;
	private int selectedItem;
	private String[] drawerTitles;
	private TypedArray drawerIcon;

	public DrawerAdapter(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		drawerTitles = mainActivity.getResources().getStringArray(R.array.drawerTitles);
		drawerIcon = mainActivity.getResources().obtainTypedArray(R.array.drawerIcons);
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView itemTitle;
		private AppCompatImageView imageView;
		private View background;

		public ViewHolder(View itemView, int ViewType) {
			super(itemView);

			if (ViewType == TYPE_ITEM) {
				itemView.setOnClickListener(this);
				itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
				imageView = (AppCompatImageView) itemView.findViewById(R.id.ItemIcon);
				background = itemView.findViewById(R.id.background);
			} else {
				imageView = (AppCompatImageView) itemView.findViewById(R.id.image);
			}
		}

		@Override
		public void onClick(View view) {
			mainActivity.onClickItem(getAdapterPosition());
		}
	}

	@Override
	public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == TYPE_ITEM) {
			View v = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.item_drawer, parent, false);

			return new ViewHolder(v, viewType);

		} else if (viewType == TYPE_HEADER) {
			View v = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.header_drawer, parent, false);

			return new ViewHolder(v, viewType);
		}
		return null;
	}

	@Override
	public void onBindViewHolder(DrawerAdapter.ViewHolder holder, int position) {
		if (!isPositionHeader(position)) {
			holder.itemTitle.setText(drawerTitles[position - 1]);

			holder.imageView.setImageResource(drawerIcon.getResourceId(position - 1, 0));
			if (selectedItem == position) {
				holder.background.setVisibility(View.VISIBLE);
			} else {
				holder.background.setVisibility(View.GONE);
			}
		}
	}

	public void setSelectedItem(int item) {
		selectedItem = item;
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return drawerTitles.length + 1;
	}

	@Override
	public int getItemViewType(int position) {
		if (isPositionHeader(position)) {
			return TYPE_HEADER;
		} else {
			return TYPE_ITEM;
		}
	}

	private boolean isPositionHeader(int position) {
		return position == 0;
	}
}