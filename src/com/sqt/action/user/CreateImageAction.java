package com.sqt.action.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CreateImageAction extends ActionSupport {
	private ByteArrayInputStream inputStream;
    private static int WIDTH = 60;

    private static int HEIGHT = 20;

    public ByteArrayInputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream)
    {
        this.inputStream = inputStream;
    }
    private static String createRandom()
    {
        String str = "0123456789qwertyuiopasdfghjklzxcvbnm";
        char[] rands = new char[4];
        Random random = new Random();
        for (int i = 0; i < 4; i++)
        {
            rands[i] = str.charAt(random.nextInt(36));
        }
        return new String(rands);
    }

    private void drawBackground(Graphics g)
    {
        // ������
        g.setColor(new Color(0xDCDCDC));

        g.fillRect(0, 0, WIDTH, HEIGHT);

        // ������� 120 �����ŵ�

        for (int i = 0; i < 120; i++)
        {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
    }

    private void drawRands(Graphics g, String rands)
    {
        g.setColor(Color.BLACK);

        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));

        // �ڲ�ͬ�ĸ߶��������֤���ÿ���ַ�

        g.drawString("" + rands.charAt(0), 1, 17);
        g.drawString("" + rands.charAt(1), 16, 15);
        g.drawString("" + rands.charAt(2), 31, 18);
        g.drawString("" + rands.charAt(3), 46, 16);
        System.out.println(rands);

    }

    @Override
    public String execute() throws Exception
    {
    	System.out.println("----��ȡ��֤��----");
        HttpServletResponse response = ServletActionContext.getResponse();
       
        // �����������Ҫ�����ͼƬ
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        String rands = createRandom();
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // ����ͼ��
        drawBackground(g);
        drawRands(g, rands);
        // ����ͼ�� �Ļ��� ���̣� ���ͼ��
        g.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
        ByteArrayInputStream input = new ByteArrayInputStream(outputStream
                .toByteArray());
        this.setInputStream(input);
        System.out.println("rands:"+rands);
        Map session = ActionContext.getContext().getSession();
        session.put("checkCode", rands);
        
        input.close();
        outputStream.close();
        return SUCCESS;
    }
}
