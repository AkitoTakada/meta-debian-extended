SUMMARY = "Text shaping library"
DESCRIPTION = "HarfBuzz is an OpenType text shaping engine."
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/HarfBuzz"
BUGTRACKER = "https://bugs.freedesktop.org/enter_bug.cgi?product=HarfBuzz"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=e021dd6dda6ff1e6b1044002fc662b9b \
                    file://src/hb-ucdn/COPYING;md5=994ba0f1295f15b4bda4999a5bbeddef \
"

inherit debian-package
require recipes-debian/sources/harfbuzz.inc
DEBIAN_QUILT_PATCHES = ""

DEPENDS = "glib-2.0 cairo fontconfig freetype"

inherit autotools pkgconfig lib_package gtk-doc

PACKAGECONFIG ??= "icu"
PACKAGECONFIG[icu] = "--with-icu,--without-icu,icu"

EXTRA_OECONF = " \
    --with-cairo \
    --with-fontconfig \
    --with-freetype \
    --with-glib \
    --without-graphite2 \
"

do_configure_prepend() {
    # This is ancient and can get used instead of the patched one we ship,
    # so delete it. In 1.8.9 this should be removed upstream.
    rm -f ${S}/m4/pkg.m4
}

PACKAGES =+ "${PN}-icu ${PN}-icu-dev"

LEAD_SONAME = "libharfbuzz.so"

FILES_${PN}-icu = "${libdir}/libharfbuzz-icu.so.*"
FILES_${PN}-icu-dev = "${libdir}/libharfbuzz-icu.la \
                       ${libdir}/libharfbuzz-icu.so \
                       ${libdir}/pkgconfig/harfbuzz-icu.pc \
"

BBCLASSEXTEND = "native"
