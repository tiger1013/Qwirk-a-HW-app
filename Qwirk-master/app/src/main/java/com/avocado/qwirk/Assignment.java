package com.avocado.qwirk;
import java.util.ArrayList;


public class Assignment {
    int id;
    String clss;
    String text;
    String title;
    ArrayList<Block> blocks;
    int days;
    int tint;

    public Assignment(int id, String clss, String text, String title, int days, int tint) {
        this.id = id;
        this.text = text;
        this.clss = clss;
        this.title = title;
        this.days = days;
        this.tint = tint;
        this.blocks = new ArrayList<Block>();
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getClss() {
        return clss;
    }

    public String getTitle() {
        return title;
    }

    public int getDays() {
        return days;
    }

    public int getTint() {
        return tint;
    }

    public int getNumBlocks(){ return blocks.size();}

    public Block getBlock(int i){ return this.blocks.get(i);}

    public void addBlock(Block block){
        block.setColor(tint);
        this.blocks.add(block);}

    public void addBlocks(Block[] blocks){
        for(int i = 0; i < blocks.length; i++){
            blocks[i].setColor(tint);
            this.blocks.add(blocks[i]);
        }
    }

    public boolean hasBlock(int day, int startTime){
        for(int i = 0; i < blocks.size(); i++){
            int iDay = blocks.get(i).getDay();
            int iStart = blocks.get(i).getStartTime();
            int iEnd = blocks.get(i).getEndTime();
            if(day == iDay) {
                if (startTime >= iStart && startTime <= iEnd) {
                    return true;
                }
            }
        }

        return false;
    }

    public void deleteBlock(int day, int startTime){
        for(int i = 0; i < blocks.size(); i++){
            int iDay = blocks.get(i).getDay();
            int iStart = blocks.get(i).getStartTime();
            int iEnd = blocks.get(i).getEndTime();
            if(day == iDay) {
                if (startTime >= iStart && startTime <= iEnd) {
                    blocks.remove(i);
                }
            }
        }

    }

    public void deleteBlocks(){
        this.blocks = new ArrayList<Block>();
    }

    //when blocks are added via allocateTime(), each cell is added as a separate block. This function would make block storage more efficient.
    public void consolidateBlocks(){}

}
