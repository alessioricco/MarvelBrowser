package it.alessioricco.marvelbrowser.activities.list.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.alessioricco.marvelbrowser.R;
import it.alessioricco.marvelbrowser.activities.details.ComicsDetailActivity;
import it.alessioricco.marvelbrowser.activities.details.ComicsDetailFragment;
import it.alessioricco.marvelbrowser.models.comics.Comics;
import it.alessioricco.marvelbrowser.models.comics.Result;
import it.alessioricco.marvelbrowser.utils.ImageDownloader;

/**
 * adapter for the recycle view (main list)
 */
public class ComicsListViewAdapter
        extends RecyclerView.Adapter<ComicsListViewAdapter.ViewHolder> {

    final private Boolean twoPane;

    final private AppCompatActivity appCompatActivity;
    final Comics comics;

    public ComicsListViewAdapter(Comics data, AppCompatActivity appCompatActivity, Boolean twoPane) {
        this.twoPane = twoPane;
        this.appCompatActivity = appCompatActivity;
        comics = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comics_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Context context = appCompatActivity.getApplication().getApplicationContext();
        final Result result = comics.getData().getResults().get(position);

        holder.title.setText(result.getTitle());
        ImageDownloader.go(context, result.getThumbnail().getPath(), holder.cover);

        holder.viewRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (twoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putSerializable(ComicsDetailFragment.ARG_COMICBOOK, result);
                    ComicsDetailFragment fragment = new ComicsDetailFragment();
                    fragment.setArguments(arguments);
                    appCompatActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.comicbook_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ComicsDetailActivity.class);
                    intent.putExtra(ComicsDetailFragment.ARG_COMICBOOK, (Serializable) result);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return comics.getData().getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final View viewRow;

        @InjectView(R.id.title)
        TextView title;

        @InjectView(R.id.cover)
        ImageView cover;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            viewRow = view;

        }

    }
}
