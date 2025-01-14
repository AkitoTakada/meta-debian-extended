# base recipe: meta/recipes-multimedia/alsa/alsa-lib_1.1.8.bb
# base branch: warrior
# base commit: 51254af4b052464fa0cfb94d8b3d02025ca063dc

SUMMARY = "ALSA sound library"
HOMEPAGE = "http://www.alsa-project.org"
BUGTRACKER = "http://alsa-project.org/main/index.php/Bug_Tracking"
SECTION = "libs/multimedia"
LICENSE = "LGPLv2.1 & GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=a916467b91076e631dd8edb7424769c7 \
                    file://src/socket.c;md5=dd1bc7f44872690224d89c1a9806e495;beginline=1;endline=26 \
                    "

inherit debian-package
require recipes-debian/sources/alsa-lib.inc

inherit autotools pkgconfig

EXTRA_OECONF += " \
    ${@bb.utils.contains('TARGET_FPU', 'soft', '--with-softfloat', '', d)} \
    --disable-python \
"

PACKAGES =+ "alsa-server alsa-conf alsa-doc"

FILES_alsa-server = "${bindir}/*"
FILES_alsa-conf = "${datadir}/alsa/"

RDEPENDS_${PN}_class-target = "alsa-conf"

# upgrade path
RPROVIDES_${PN} = "libasound"
RREPLACES_${PN} = "libasound"
RCONFLICTS_${PN} = "libasound"

RPROVIDES_${PN}-dev = "alsa-dev"
RREPLACES_${PN}-dev = "alsa-dev"
RCONFLICTS_${PN}-dev = "alsa-dev"

RPROVIDES_alsa-conf = "alsa-conf-base"
RREPLACES_alsa-conf = "alsa-conf-base"
RCONFLICTS_alsa-conf = "alsa-conf-base"

BBCLASSEXTEND = "native nativesdk"
