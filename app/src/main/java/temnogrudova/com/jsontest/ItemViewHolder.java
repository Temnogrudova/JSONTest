package temnogrudova.com.jsontest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView txtUserId;
    private TextView txtId;
    private TextView txtTitle;
    private TextView txtBody;

    public ItemViewHolder(final View parent, TextView txtUserId, TextView txtId, TextView txtTitle, TextView txtBody) {
        super(parent);
        this.txtUserId = txtUserId;
        this.txtId = txtId;
        this.txtTitle = txtTitle;
        this.txtBody = txtBody;
    }

    public static ItemViewHolder newInstance(View parent) {
        TextView txtUserId = (TextView)parent.findViewById(R.id.user_id);
        TextView txtId = (TextView)parent.findViewById(R.id.id);
        TextView txtTitle = (TextView)parent.findViewById(R.id.title);
        TextView txtBody = (TextView)parent.findViewById(R.id.body);
        return new ItemViewHolder(parent, txtUserId, txtId, txtTitle, txtBody);
    }

    public void setItemText( String userId, String id, String title, String body) {
        txtUserId.setText(userId);
        txtId.setText(id);
        txtTitle.setText(title);
        txtBody.setText(body);
    }
}

