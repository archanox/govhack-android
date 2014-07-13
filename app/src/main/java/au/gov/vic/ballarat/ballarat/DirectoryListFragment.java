package au.gov.vic.ballarat.ballarat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;


import au.gov.vic.ballarat.ballarat.db.BallaratContentProvider;
import au.gov.vic.ballarat.ballarat.db.BallaratDB;
import au.gov.vic.ballarat.ballarat.dummy.DummyContent;

public class DirectoryListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SimpleCursorAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DirectoryListFragment() {
    }

    public static DirectoryListFragment newInstance(int sectionNumber) {
        DirectoryListFragment fragment = new DirectoryListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fillData();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void fillData() {
        String[] from = new String[] {
                BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN
        };
        getLoaderManager().initLoader(0, null, this);
        mAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, null, from, new int[] { android.R.id.text1 }, 0);
        setListAdapter(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                BallaratDB.DIRECTORY__ID_COLUMN,
                BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN
        };
        CursorLoader cursorLoader = new CursorLoader(getActivity(),
                BallaratContentProvider.CATEGORY_URI, projection, null, null, BallaratDB.DIRECTORY_CATEGORYNAME_COLUMN);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cursor c = ((SimpleCursorAdapter)l.getAdapter()).getCursor();
        c.moveToPosition(position);

        Intent intent = new Intent(getActivity(), DirectoryCategoryActivity.class);
        intent.putExtra("categoryName", c.getString(1));
        startActivity(intent);
    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
