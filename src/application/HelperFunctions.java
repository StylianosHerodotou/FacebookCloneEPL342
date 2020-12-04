package application;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBox;

public class HelperFunctions {
	static int initXlevel = 0;
	static int initYlevel = 2;
	
	
	
	protected static boolean is_field_sensitive(String filed_name) {
		String[] sensitive_info = { "password", "SSN","id","user_id","UserID" };
		boolean is_sensitive = false;
		for (int index = 0; index < sensitive_info.length; index++) {
			if (filed_name.equals(sensitive_info[index])) {
				is_sensitive = true;
				break;
			}
		}
		return is_sensitive;
	}
	
	static void addItemLabel(Field field, Object object, ArrayList<Node> retriveFields, int tabIndex, UserView view,boolean canEdit, boolean mine, boolean isInsert) {
		String fieldName=field.getName();
		String fieldType= field.getType().getSimpleName();

		if(fieldType==null) {
			System.out.println(fieldName);
		}

		Object fieldValue = null;
		try {
			fieldValue = field.get(object);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(fieldValue!=null) {
			try {
				if(fieldName.equals("gender")){
					if((boolean)fieldValue==true)
						addTextLabelRow("Female", retriveFields);
					else
						addTextLabelRow("Male", retriveFields);
				}
				else if (fieldName.equals("pictures")){
					addAlbumLabel((ArrayList)fieldValue, retriveFields, tabIndex, view,canEdit,mine,isInsert);
				}
				else{
					addTextLabelRow(fieldValue.toString(), retriveFields);
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			addTextLabelRow("null", retriveFields);
		}

	}

	protected static String ArrayListToString(ArrayList<Object> lista) {
		String s= new String();
		if(lista!=null) {
			for (int index=0; index<lista.size(); index++) {
				String item=lista.get(index).toString();
				if(index==lista.size()-1) {
					s=s+item+"\n";
				}
				else {
					s=s+item+" * ";
				}
			}
		}
		return s;
	}

	protected static void addFielditemInGrid(GridPane grid, String fieldName, int field_index,ArrayList<Node> nodes) {
		grid.add(new Label(fieldName+": "), initXlevel + 1,
				field_index +initYlevel);// adding 2 because i start from (2,3)
		grid.add( nodes.get(field_index), initXlevel + 2,
				field_index +initYlevel++);// adding 2 because i start from (2,3)
	}

	protected static void addTextFieldRow( String fieldValue,ArrayList<Node> nodes) {
		if(fieldValue!=null)
			nodes.add(new TextField(fieldValue));
		else
			nodes.add(new TextField());
	}

	protected static void addTextLabelRow( String fieldValue,ArrayList<Node> nodes) {
		if(fieldValue!=null)
			nodes.add(new Label(fieldValue));
		else
			nodes.add(new Label());
	}
	protected static void addIsVerifiedField( boolean fieldValue,ArrayList<Node> nodes) {
		String[] genders= {"True","False"};
		ComboBox isVerifiedBox = new ComboBox(FXCollections.observableArrayList(genders));
		if(fieldValue==true)
			isVerifiedBox.getSelectionModel().selectFirst();
		else
			isVerifiedBox.getSelectionModel().select(1); // select second.
		nodes.add(isVerifiedBox);
	}

	protected static void addGenderField( boolean b,ArrayList<Node> nodes ) {
		String[] genders= {"Male","Female"};
		ComboBox genderBox = new ComboBox(FXCollections.observableArrayList(genders));
		if(b==false) {
			genderBox.getSelectionModel().selectFirst();
		}
		else {
			genderBox.getSelectionModel().select(1); // select second.
		}
		nodes.add(genderBox);

	}

//	protected String[] locationToStringArray(HashMap<String, Integer> locationHashmap ) {
//
//		String[] array= new String[locationHashmap.size()];
//		for(int index=0; index<array.length; index++) {
//			array[index]=
//		}
//
//	}

	protected static void addLocationField(Location location, ArrayList<Node> nodes, UserView view) {
		HashMap<String, Integer> locationHashmap = view.controller.getLocations();
		String [] locations= AuthenticationController.convert(locationHashmap.keySet());
		String locationName=locations[0];
		if(location!=null) {
			locationName= location.getName();
		}
		ComboBox hometownBox = new ComboBox(FXCollections.observableArrayList(locations));
		hometownBox.getSelectionModel().select(locationName);
		nodes.add(hometownBox);
		}
	protected static void addDateField( Date fieldValue, ArrayList<Node> nodes) {
		DatePicker datePicker = new DatePicker();
		if(fieldValue!=null)
		{
			datePicker.setValue(fieldValue.toLocalDate());
		}else {
			datePicker = new DatePicker(LocalDate.now());;
		}
		nodes.add(datePicker);
}
	protected static Node addPictureLabel( Picture fieldValue, int tabIndex,UserView view,boolean canEdit, boolean mine, boolean isInsert) {
		HBox box=null;
		if(fieldValue==null) {
			box = new HBox();
		}
		else {
			box = new HBox(view.getItemView(tabIndex, fieldValue,canEdit, mine, isInsert));
		}
		return box;
}
	
	
	protected static Node addPictureField( Picture fieldValue, int tabIndex,UserView view,boolean canEdit,boolean mine, boolean isInsert) {
		HBox box=null;
		if(fieldValue==null) {
			box = new HBox();
		}
		else {
			box = new HBox(view.getFormView(tabIndex, fieldValue,canEdit,mine,isInsert));
		}
		return box;
}
	protected static void addAlbumLabel(ArrayList<Picture> pictures, ArrayList<Node> nodes, int tabIndex,UserView view,boolean canEdit, boolean mine, boolean isInsert) {
		int ylevel=0;
		int xlevel=2;
		GridPane grid = new GridPane();	
		if(pictures!=null) {
			for (Picture pic : pictures) {
				grid.add(addPictureLabel(pic,tabIndex,view,canEdit,mine,isInsert),xlevel,ylevel++);
			}
		}

		nodes.add(grid);
	}
	
	protected static void addAlmbumField(ArrayList<Picture> pictures, ArrayList<Node> nodes, int tabIndex,UserView view,boolean canEdit,boolean mine, boolean isInsert) {
			
			int ylevel=0;
			int xlevel=2;
			GridPane grid = new GridPane();	
			if(pictures!=null) {
			for (Picture pic : pictures) {
				grid.add(addPictureField(pic,tabIndex,view,canEdit,mine, isInsert),xlevel,ylevel++);
			}
			}
			nodes.add(grid);
		}
	

	protected static Object addItemField(Field field, Object object, ArrayList<Node> retriveFields,
			int fieldIndex,UserView view, boolean forRead, int tabIndex,boolean canEdit, boolean mine, boolean isInsert) throws IllegalArgumentException, IllegalAccessException {
		String fieldName=field.getName();
//		String fieldType=(String) allPossibleFields.get(fieldName);
		String fieldType= field.getType().getSimpleName();
		Object fieldValue=null;
		if(fieldType==null) {
			System.out.print("field name "+fieldName);
		}

		switch(fieldType) {
			case "String":
				if(forRead) {
				addTextFieldRow((String) field.get(object),retriveFields);
				break;
				}
				else
				return ((TextField) retriveFields.get(fieldIndex)).getText();

			case "int" :
				if(forRead) {
				addTextFieldRow(Integer.toString((int)field.get(object)),retriveFields);
				break;
				}
				else
				return Integer.parseInt(((TextField) retriveFields.get(fieldIndex)).getText());
			case "double" :
				if(forRead) {
				addTextFieldRow(Double.toString((double)field.get(object)),retriveFields);
				break;
				}
				else
				return Double.parseDouble(((TextField) retriveFields.get(fieldIndex)).getText());
			case "ArrayList":
				if(forRead) {
					if(fieldName.equals("comments")) {
						System.out.print(" field "+fieldName + "field type "+fieldType );
						String comments= toStringCommnets((ArrayList<Comment>)field.get(object) );
						addTextFieldRow(comments,retriveFields);

					}else if (fieldName.equals("pictures")){
						addAlbumLabel((ArrayList<Picture>) field.get(object), retriveFields, tabIndex, view,canEdit,mine,isInsert);
					}else {
						addTextFieldRow(ArrayListToString((ArrayList<Object>) field.get(object)),retriveFields);
					}
					break;
				}
				else {
					if(fieldName.equals("comments")) {
						return null;

					}else if (fieldName.equals("pictures")){
						return null;
					}else {
						return new ArrayList<String>(Arrays.asList(((TextField) retriveFields.get(fieldIndex)).getText().split("\\*",0)));
					}
				}
			case "Date":
				if(forRead) {
				addDateField((Date)field.get(object),retriveFields);
				break;}
				else
					return Date.valueOf( ((DatePicker)retriveFields.get(fieldIndex)).getValue());
			case "Timestamp":
				if(forRead) {
					Object value=field.get(object);
					if(value!=null)
						addTextFieldRow(field.get(object).toString(),retriveFields);
					else
						addTextFieldRow("null",retriveFields);
				break;}
				else {
					String temp=((TextField) retriveFields.get(fieldIndex)).getText();
					if(temp.length()<8) {
						return new Timestamp(System.currentTimeMillis());
					}
					else return Timestamp.valueOf(temp);
				}
			case "boolean":
				if(field.getName().equals("gender"))
				{
					if(forRead) {
					addGenderField((boolean)field.get(object),retriveFields);
					break;}
					else {
						String strGender=(String) ((ComboBox)retriveFields.get(fieldIndex)).getValue();
						boolean gender=true;
						if(strGender.equals("Male")) {
							gender=false;
						}
						return gender;
					}
				}else if((field.getName().equals("isVerified"))) {
					if(forRead) {
					addIsVerifiedField((boolean )field.get(object),retriveFields);
					break;}
					else {
						String isVerified= (String) ((ComboBox)retriveFields.get(fieldIndex)).getValue();
						if(isVerified.equals("True"))
							return true;
						else
							return false;
					}
				}
			break; // dame pai kati lathos??
			case "Location":
				if(forRead) {
				addLocationField((Location )field.get(object),retriveFields,view);
				break;}
				else {
					HashMap<String, Integer> locationHashmap = view.controller.getLocations();
					String strLocation= (String) ((ComboBox)retriveFields.get(fieldIndex)).getValue();
					System.out.print(strLocation);
					Location hometown=new Location(locationHashmap.get(strLocation), strLocation);
					return hometown;
				}
			case "Privacy":
				if(forRead) {
					if(field.get(object)!=null)
						addTextFieldRow(((Privacy) field.get(object)).name, retriveFields);
					else
						addTextFieldRow("OPEN", retriveFields);

				break;}
				else
				return new Privacy(((TextField) retriveFields.get(fieldIndex)).getText());
			default:
				System.out.print("there was a new field "+fieldName + "field type "+fieldType );
			break;
		}
		return null;
	}


	private static String toStringCommnets(ArrayList<Comment> object) {
		String s= new String();
		if(object==null) {
			return s;
		}
		for (int index=0; index<object.size(); index++) {
			Comment comment = object.get(index);
			String stringComment =comment.toString()+"*\n";
			s=s+stringComment;
		}
		return s;
	}

	static ArrayList<Object> getDataFromFields(Object object,ArrayList<Field> fields, ArrayList<Node> retriveFields,
			UserView view, int tabIndex,boolean canEdit, boolean mine, boolean isInsert) throws IllegalArgumentException, IllegalAccessException{
		 ArrayList<Object> data= new ArrayList<Object>();
		 for (int fieldIndex=0; fieldIndex<retriveFields.size(); fieldIndex++) {
			 data.add(addItemField( fields.get(fieldIndex), object,retriveFields,fieldIndex,view, false,tabIndex,canEdit,mine,isInsert));
		 }
		 return data;

	}



	static ArrayList<Field> getNonSensitiveFields(Object object, Field[] all_fields){
		ArrayList<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < all_fields.length; i++) {
			String field_name = all_fields[i].getName();
			if (is_field_sensitive(field_name) == false) {
				fields.add(all_fields[i]);
//				grid.addColumn(fields.size(), new Label(field_name));
			}
		}
		return fields;

	}

	private static ArrayList<Field> getBothSensitiveAndNonSensitiveFields(Object object, Field[] all_fields){
		ArrayList<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < all_fields.length; i++) {
			String field_name = all_fields[i].getName();
				fields.add(all_fields[i]);
//				grid.addColumn(fields.size(), new Label(field_name));
		}
		return fields;

	}

////////////////////////////////////////////////////////////////////
	
	
	
}
