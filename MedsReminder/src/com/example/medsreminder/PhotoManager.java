package com.example.medsreminder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

public class PhotoManager extends Activity {
	
	private String photoPath;
	
	//Dialog Options
	final CharSequence[] dialogItems = { "Take Photo", "Choose from Library","Cancel" };
	//Dialog Title
	public final static String DIALOG_TITLE ="ADD PHOTO!";
	//Needed for Photo
	static final int REQUEST_IMAGE_CAPTURE = 1;
	//For Dialog Intent
	public Intent dialogIntent;
	
	Context innerContext;
	
	public PhotoManager(Context context){
		this.innerContext = context;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	public void ChooseOption(){
		try{


			AlertDialog.Builder builder = new AlertDialog.Builder(this.innerContext);
			builder.setTitle(DIALOG_TITLE);
			builder.setItems(dialogItems, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int item) {
					try{
						if (dialogItems[item].equals("Take Photo")) {
							dialogIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							//File f = new File(android.os.Environment
							//        .getExternalStorageDirectory(), "temp.jpg");

							File file = createImageFile(innerContext);

							dialogIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
							try {
								startActivityForResult(dialogIntent, REQUEST_IMAGE_CAPTURE);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else if (dialogItems[item].equals("Choose from Library")) {
							Intent intent = new Intent(
									Intent.ACTION_PICK,
									android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
							intent.setType("image/*");
							//startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
						} else if (dialogItems[item].equals("Cancel")) {
							dialog.dismiss();
						}
					}
					catch(Exception e){
						String s = e.getMessage();

					}
				}
			});
			builder.show();
		}
		catch(Exception e){
			String s = e.getMessage();
		}
	}
	
	private File createImageFile(Context context) throws IOException{
	
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";


		File storageDir = innerContext.getExternalFilesDir(null);

		File image = File.createTempFile(
				imageFileName,  /* prefix */
				".jpg",         /* suffix */
				storageDir      /* directory */
				);

		// Save a file: path for use with ACTION_VIEW intents
		this.photoPath = image.getAbsolutePath();
		return image;
		
	}
}
