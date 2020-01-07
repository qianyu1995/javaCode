package com.higanbana.io.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class FileUtils
{
	public static void getChannel()
	{
		String source = "src/resources/a.txt";
		String target = "src/resources/b.txt";
		try (
				FileInputStream inputStream = new FileInputStream(source);
				FileOutputStream outputStream = new FileOutputStream(target);
				ReadableByteChannel sourceChannel = inputStream.getChannel();
				WritableByteChannel targetChannel = outputStream.getChannel();
		)
		{
			ByteBuffer buffer = ByteBuffer.allocateDirect(20*1024);
			while ( sourceChannel.read(buffer) != -1 )
			{
				buffer.flip();
				while ( buffer.hasRemaining() )
				{
					targetChannel.write(buffer);
				}
				buffer.clear();
			}
		} catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		} catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		//getChannel();
		
	}
}
