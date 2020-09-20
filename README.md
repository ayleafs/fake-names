# FakeName Mod
This mod allows you to give yourself a fake name on your screen.

## Video
https://www.youtube.com/watch?v=ukCQ0lhPZjY

## How to use?
Just do `/fakename <your fake IGN>`. As far as you're concerned, this should work for you.

## How does this work?
Uses ASM to inject bytecode into methods in the FontRenderer class and replaces all instances of your IGN in certain methods. Looks like this:
```diff
/**
 * Render a single line string at the current (posX,posY) and update posX
 */
private void renderStringAtPos(String text, boolean shadow) {
+   text = NameUtils.apply(text);    

    // ... all the code that makes text render ...
}
```

## Notes
- This could potentially conflict with NickHider mod
- If you're nicked your nickname will not be replaced
- I will not be adding tab completion, fight me
