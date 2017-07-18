/**
 * Copyright (C) 2014 android10.org. All rights reserved.
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
import com.javiertarazaga.instasearch.presentation.model.UserModel;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adaptar that manages a collection of {@link UserModel}.
 */
public class MediasAdapter extends RecyclerView.Adapter<MediasAdapter.UserViewHolder> {

  public interface OnItemClickListener {
    void onUserItemClicked(UserModel userModel);
  }

  private List<UserModel> usersCollection;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject MediasAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.usersCollection = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.usersCollection != null) ? this.usersCollection.size() : 0;
  }

  @Override public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_post, parent, false);
    return new UserViewHolder(view);
  }

  @Override public void onBindViewHolder(UserViewHolder holder, final int position) {
    final UserModel userModel = this.usersCollection.get(position);
    holder.tv_username.setText(userModel.getFullName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (MediasAdapter.this.onItemClickListener != null) {
          MediasAdapter.this.onItemClickListener.onUserItemClicked(userModel);
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setUsersCollection(Collection<UserModel> usersCollection) {
    this.validateUsersCollection(usersCollection);
    this.usersCollection = (List<UserModel>) usersCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateUsersCollection(Collection<UserModel> usersCollection) {
    if (usersCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class UserViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.iv_user_image) ImageView iv_user_image;
    @Bind(R.id.tv_username) TextView tv_username;
    @Bind(R.id.iv_post) ImageView iv_post;
    @Bind(R.id.tv_post_comment) TextView tv_post_comment;

    UserViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}