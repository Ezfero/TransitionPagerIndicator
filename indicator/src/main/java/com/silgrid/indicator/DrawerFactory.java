package com.silgrid.indicator;

class DrawerFactory {
	static Drawer create(int enumValue, int pagesAmount, int indicatorColor, int indicatorSize, Drawer.Callback callback) {
		switch (enumValue) {
			case 0:
				return new CircleDrawer(pagesAmount, indicatorColor, indicatorSize, callback);
			case 1:
				return new SquareDrawer(pagesAmount, indicatorColor, indicatorSize, callback);
			case 2:
				return new DiamondDrawer(pagesAmount, indicatorColor, indicatorSize, callback);
			default:
				return new CircleDrawer(pagesAmount, indicatorColor, indicatorSize, callback);
		}
	}
}
