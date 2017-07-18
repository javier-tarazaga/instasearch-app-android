/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.javiertarazaga.instasearch.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.model.MediaModel;
import com.squareup.picasso.Picasso;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adapter that manages a collection of {@link MediaModel}.
 */
public class MediasAdapter extends RecyclerView.Adapter<MediasAdapter.MediaViewHolder> {

  private final Context context;

  public interface OnItemClickListener {
    void onMediaItemClicked(MediaModel mediaModel);
  }

  private List<MediaModel> mediaCollection;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject MediasAdapter(Context context) {
    this.context = context;
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.mediaCollection = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.mediaCollection != null) ? this.mediaCollection.size() : 0;
  }

  @Override public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_post, parent, false);
    return new MediaViewHolder(view);
  }

  @Override public void onBindViewHolder(MediaViewHolder holder, final int position) {
    final MediaModel mediaModel = this.mediaCollection.get(position);
    holder.tv_username.setText(mediaModel.getUser().getUsername());

    // TODO - Setup Picasso through DI instead. U2020 project style
    Picasso.with(this.context)
        .load(mediaModel.getUser().getProfilePicture())
        .into(holder.iv_user_image);
    Picasso.with(this.context)
        .load(mediaModel.getImages().getLowResolution().getUrl())
        .into(holder.iv_post);

    if (mediaModel.getCaption() != null && mediaModel.getCaption().getText() != null) {
      holder.tv_post_comment.setVisibility(View.VISIBLE);
      holder.tv_post_comment.setText(mediaModel.getCaption().getText());
    } else {
      holder.tv_post_comment.setVisibility(View.GONE);
    }

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (MediasAdapter.this.onItemClickListener != null) {
          MediasAdapter.this.onItemClickListener.onMediaItemClicked(mediaModel);
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setMediaCollection(Collection<MediaModel> mediaCollection) {
    this.validateMediaCollection(mediaCollection);
    this.mediaCollection = (List<MediaModel>) mediaCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateMediaCollection(Collection<MediaModel> mediaCollection) {
    if (mediaCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class MediaViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.iv_user_image) ImageView iv_user_image;
    @Bind(R.id.tv_username) TextView tv_username;
    @Bind(R.id.iv_post) ImageView iv_post;
    @Bind(R.id.tv_post_comment) TextView tv_post_comment;

    MediaViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
