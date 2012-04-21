package com.glam.twig.parser.JSON;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.glam.twig.bean.BaseBean;
import com.glam.twig.bean.TimeLine;
import com.glam.twig.parser.BaseJSONParser;

public class TimeLineJSONParser extends BaseJSONParser {
	private long sinceId = -1;
	private long maxId = -1;

	@Override
	public ArrayList<? extends BaseBean> parseForList(BufferedReader in, int lmethod) {
		ArrayList<TimeLine> arrTimeLine = new ArrayList<TimeLine>();
		try {
			String inputLine;
			String jsontext = "";
			while ((inputLine = in.readLine()) != null) {
				jsontext = inputLine;
			}

			JSONArray entries = new JSONArray(jsontext);
			int i;
			JSONObject objValue = null;
			for (i = 0; i < entries.length(); i++) {
				JSONObject post = entries.getJSONObject(i);
				TimeLine timeLine = new TimeLine();
				int indexRT = post.toString().indexOf("retweeted_status");
				long tempId;
				if (indexRT >= 0) {
					objValue = post.getJSONObject("retweeted_status");
					JSONObject objUser = objValue.getJSONObject("user");
					JSONObject objMainUser = post.getJSONObject("user");
					timeLine.id = post.getString("id");
					tempId = Long.parseLong(post.getString("id"));
					timeLine.favorited = post.getString("favorited");
					timeLine.userid = objUser.getString("id");
					timeLine.retweet_count = post.getString("retweet_count");
					timeLine.created_at = post.getString("created_at");
					timeLine.text = objValue.getString("text");
					timeLine.retweeted_status = post.getString("text");
					timeLine.screenname = objUser.getString("screen_name");
					timeLine.profile_image_url = objUser
							.getString("profile_image_url");
					Pattern p = Pattern.compile("(?>)([\\w&;\\s\\.]+.?)(?=<)");
					Matcher m = p.matcher(post.getString("source"));
					if (m.find()) {
						timeLine.source = m.group(0);
					} else {
						timeLine.source = post.getString("source");
					}
					timeLine.rt_name = objMainUser.getString("screen_name");
					timeLine.reply_name = post
							.getString("in_reply_to_screen_name");
					timeLine.replytostatusid = post
							.getString("in_reply_to_status_id");
					timeLine.retweeted = true;
				} else {
					JSONObject objUser = post.getJSONObject("user");
					timeLine.id = post.getString("id");
					tempId = Long.parseLong(post.getString("id"));
					timeLine.favorited = post.getString("favorited");
					timeLine.userid = objUser.getString("id");
					timeLine.retweet_count = post.getString("retweet_count");
					timeLine.created_at = post.getString("created_at");
					timeLine.text = post.getString("text");
					timeLine.screenname = objUser.getString("screen_name");
					timeLine.profile_image_url = objUser
							.getString("profile_image_url");
					Pattern p = Pattern.compile("(?>)([\\w&;\\s\\.]+.?)(?=<)");
					Matcher m = p.matcher(post.getString("source"));
					if (m.find()) {
						timeLine.source = m.group(0);
					} else {
						timeLine.source = post.getString("source");
					}
					timeLine.rt_name = "";
					timeLine.reply_name = post
							.getString("in_reply_to_screen_name");
					timeLine.replytostatusid = post
							.getString("in_reply_to_status_id");
					timeLine.retweeted = false;

				}
				if (lmethod == 0) // first time
				{
					if (i == 0) {
						setSinceId(tempId);
					} else if (i == entries.length() - 1) {
						setMaxId(tempId);
					}
				} else if (lmethod == 1) // load new
				{
					if (i == 0) {
						setSinceId(tempId);

					}
				} else if (lmethod == 2) {
					if (i == entries.length() - 1) {
						setMaxId(tempId);
					}
				}
				arrTimeLine.add(timeLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrTimeLine;

	}

	public void setSinceId(long sinceId) {
		this.sinceId = sinceId;
	}

	public long getSinceId() {
		return sinceId;
	}

	public void setMaxId(long maxId) {
		this.maxId = maxId;
	}

	public long getMaxId() {
		return maxId;
	}

}
