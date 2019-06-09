/**
 * Mikronia Game Library
 * 
 * @author Thaynan M. Silva
 */
module mikronia.glib {
	
	requires transitive java.desktop;
	requires com.jcraft.jorbis;
	
	exports com.mikronia.glib;
	exports com.mikronia.glib.vector;
	exports com.mikronia.glib.animation;

	opens com.mikronia.glib.utils;
	opens com.mikronia.glib.audio;
	opens com.mikronia.glib.vector.base;
	
}
