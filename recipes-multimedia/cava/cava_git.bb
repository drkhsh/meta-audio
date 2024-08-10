LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6eea703700f068b569f924b80fadf6da \
			"
SRC_URI = "git://github.com/karlstav/cava;protocol=https;branch=master \
           file://0001-Fix-curses-and-iniparser-autoconf.patch \
           "

PV = "0.10.2"
SRCREV="a2f314df4b6c6bf8b6800124ed185b5a2ef50288"
PROVIDES = "cava"

S = "${WORKDIR}/git"

DEPENDS = "alsa-lib ncurses fftw glibc iniparser"
RDEPENDS:${PN} += " libpulse libpipewire"

inherit autotools pkgconfig

EXTRA_OECONF = "CFLAGS='-O2' --prefix=/usr"

FILES:${PN} += "/usr/share/consolefonts/cava.psf"
