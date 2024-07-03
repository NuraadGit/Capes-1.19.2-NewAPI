package me.cael.capes.mixins;

import me.cael.capes.menu.SelectorMenu;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkinOptionsScreen.class)
public class MixinSkinOptionsScreen extends GameOptionsScreen {

    private static final Identifier CAPE_OPTIONS_ICON_TEXTURE = new Identifier("capes","textures/gui/options.png");
    private final SelectorMenu selectorMenu = new SelectorMenu(this, this.gameOptions);

    public MixinSkinOptionsScreen(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    protected void init(CallbackInfo info) {
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 - 179, this.height / 6, 20, 20, 0, 0, 20, CAPE_OPTIONS_ICON_TEXTURE,32, 64, (buttonWidget) -> this.client.setScreen(selectorMenu)));
    }
}
