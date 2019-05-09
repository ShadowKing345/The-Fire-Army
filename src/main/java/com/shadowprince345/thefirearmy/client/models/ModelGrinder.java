package com.shadowprince345.thefirearmy.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGrinder extends ModelBase {

    public ModelRenderer stone;
    public ModelRenderer counter;
    public ModelRenderer axel;
    public ModelRenderer sideCounters;
    public ModelRenderer frontLegs;
    public ModelRenderer backLegs;

    public ModelGrinder() {
        stone = (new ModelRenderer(this, 0,0)).setTextureSize(128, 64);
        stone.addBox(-3.0f,-5.0f,-5.0f, 6, 10, 10);

        counter = (new ModelRenderer(this, 33, 0)).setTextureSize(128, 64);
        counter.addBox(-5.0f,0.0f,7.0f,10,1,3); // plank

        axel = (new ModelRenderer(this, 38, 5)).setTextureSize(128, 64);
        axel.addBox(-5.0f,-1.0f,-1.0f,10,2,2); // axel

        sideCounters = (new ModelRenderer(this, 17, 5)).setTextureSize(128, 64);
        sideCounters.addBox(-6.0f,1.0f,-8.0f,2,2,16); // side bar right
        sideCounters.addBox(4.0f,1.0f,-8.0f,2,2,16); // side bar left

        frontLegs = (new ModelRenderer(this, 38, 10)).setTextureSize(128, 64);
        frontLegs.addBox(-5.5f,0.5f,0.5f,1,9,1); // right
        frontLegs.addBox(4.5f,0.5f,0.5f,1,9,1); // left
        frontLegs.rotateAngleX = (float) Math.toRadians(22.5);

        backLegs = (new ModelRenderer(this, 38, 10)).setTextureSize(128, 64);
        backLegs.addBox(-5.5f,0.5f,-1.5f,1,9,1); // right
        backLegs.addBox(4.5f,0.5f,-1.5f,1,9,1); // left
        backLegs.rotateAngleX = (float) Math.toRadians(-22.5);

    }

    public void renderAll() {
        float scaleOfOne = 0.0625f;
        stone.render(scaleOfOne);
        counter.render(scaleOfOne);
        axel.render(scaleOfOne);
        sideCounters.render(scaleOfOne);
        frontLegs.render(scaleOfOne);
        backLegs.render(scaleOfOne);
    }
}
