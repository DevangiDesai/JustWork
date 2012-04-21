package com.glam.twig;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.glam.twig.api.ApiFactory;
import com.glam.twig.api.ApiTypes;
import com.glam.twig.api.TwigApi;
import com.glam.twig.bean.BaseBean;

public abstract class AbstractActivity extends Activity {
	private ProgressDialog dialog;
	private int buttonViewResId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.base_layout);
		dialog = new ProgressDialog(this);

		super.onCreate(savedInstanceState);
	}

	protected class ActionInput {
		ActionTypes type;
		HashMap<String, String> params;
		int method;
	}

	public class CommonAsyncTask extends
			AsyncTask<ActionInput, Integer, ArrayList<? extends BaseBean>> {

		protected void onPreExecute() {
			runOnUiThread(new Runnable() {
				public void run() {
					dialog.setMessage(getString(R.string.Loading));
					dialog.show();
					dialog.setCancelable(false);

				}
			});
		}

		@Override
		protected ArrayList<? extends BaseBean> doInBackground(
				ActionInput... actionInput) {
			return ((TwigApi) ApiFactory.getApi(ApiTypes.TWIG)).doAction(
					actionInput[0].type, actionInput[0].params, actionInput[0].method);
		}

		@Override
		protected void onPostExecute(ArrayList<? extends BaseBean> result) {
			super.onPostExecute(result);
			setScreenData(result);
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}
	}

	protected abstract void setScreenData(ArrayList<? extends BaseBean> result);

	OnClickListener baseButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			buttonViewResId = v.getId();
			setDataClick(buttonViewResId);
		}
	};

	protected abstract void setDataClick(int id);

}
