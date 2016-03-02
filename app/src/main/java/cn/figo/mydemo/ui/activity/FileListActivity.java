package cn.figo.mydemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import cn.figo.mydemo.R;


public class FileListActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    VideoAdapter adapter;
    boolean sortByName = false;

    @Bind(R.id.fileListView)
    ListView fileListView;

    @OnItemClick(R.id.fileListView)
    public void fileListViewItemClick(AdapterView<?> parent, View view, final int position, final long id){
        Intent intent = new Intent(FileListActivity.this, VideoActivity.class);
        intent.putExtra("videoPath", adapter.getVideoPath(position));
        intent.putExtra("videoTitle", adapter.getVideoTitle(position));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        ButterKnife.bind(this);

        adapter = new VideoAdapter(this);
        fileListView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(1, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (sortByName) {
            return new CursorLoader(this, MediaStore.Video.Media.getContentUri("external"), null, null, null,
                    "UPPER(" + MediaStore.Video.Media.DATA + ")");
        } else {
            return new CursorLoader(this, MediaStore.Video.Media.getContentUri("external"), null, null, null,
                    "UPPER(" + MediaStore.Video.Media.DISPLAY_NAME + ")");
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    class VideoAdapter extends SimpleCursorAdapter {
        public VideoAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_2, null,
                    new String[]{MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.DATA},
                    new int[]{android.R.id.text1, android.R.id.text2}, 0);
        }

        @Override
        public long getItemId(int position) {
            final Cursor cursor = getCursor();
            if (cursor.getCount() == 0 || position >= cursor.getCount()) {
                return 0;
            }
            cursor.moveToPosition(position);

            return cursor.getLong(0);
        }

        public String getVideoTitle(int position) {
            final Cursor cursor = getCursor();
            if (cursor.getCount() == 0) {
                return "";
            }
            cursor.moveToPosition(position);

            return cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
        }

        public String getVideoPath(int position) {
            final Cursor cursor = getCursor();
            if (cursor.getCount() == 0) {
                return "";
            }
            cursor.moveToPosition(position);

            return cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        }
    }
}
