package ru.hutor.entity;

import lombok.Data;
import ru.hutor.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

@Data

public class AgentEntity {
    private int id;
    private String title;
    private String agentType;
    private String address;
    private String phone;
    private String email;
    private String logoPath;
    private ImageIcon logo;
    private int priority;

    public AgentEntity(int id, String title, String agentType, String address, String phone, String email, String logoPath, int priority) {
        this.id = id;
        this.title = title;
        this.agentType = agentType;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.logoPath = logoPath;
        this.priority = priority;
        try {
            this.logo = new ImageIcon(ImageIO.read(Application.class.getClassLoader().getResource(logoPath)).getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        } catch (Exception e) {

        }
    }

    public AgentEntity(String title, String agentType, String address, String phone, String email, String logoPath, ImageIcon logo, int priority) {
        this.id = -1;
        this.title = title;
        this.agentType = agentType;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.logoPath = logoPath;
        this.logo = logo;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public ImageIcon getLogo() {
        return logo;
    }

    public void setLogo(ImageIcon logo) {
        this.logo = logo;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
