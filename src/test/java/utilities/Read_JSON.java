package utilities;

import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
public class Read_JSON 
{
	public JSONObject getJsonData() throws IOException, ParseException 
	{
		String jsonFilePath = System.getProperty("user.dir") + File.separator + "data" + File.separator
				+ "TestVagrant_RCB.json";
		FileReader reader = new FileReader(jsonFilePath);
		JSONParser jsonParser = new JSONParser();
		// Read JSON file
		Object obj = jsonParser.parse(reader);
		JSONObject jsonData = (JSONObject) obj;
		return jsonData;
	}
	
	public int get_NumberofForeignPlayers(JSONObject jsonData) throws IOException, ParseException 
	{
		int foreignPlayerCount = 0;
		JSONArray arrayData = (JSONArray) jsonData.get("player");
		for (int i = 0; i < arrayData.size(); i++) 
		{
			JSONObject player = (JSONObject) arrayData.get(i);
			if (!player.get("country").equals("India")) 
			{
				foreignPlayerCount = foreignPlayerCount + 1;
			}
		}
		return foreignPlayerCount;
	}
	
	public int get_NumberofWicketKeepers(JSONObject jsonData) throws IOException, ParseException 
	{
		int wicketKeeperCount = 0;
		JSONArray arrayData = (JSONArray) jsonData.get("player");
		for (int i = 0; i < arrayData.size(); i++) 
		{
			JSONObject player = (JSONObject) arrayData.get(i);
			if (player.get("role").equals("Wicket-keeper")) 
			{
				wicketKeeperCount = wicketKeeperCount + 1;
			}
		}
		
		return wicketKeeperCount;
	}
	
}
