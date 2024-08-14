SUMMARY = "CLI and curses mixer for PulseAudio"
HOMEPAGE = "https://github.com/GeorgeFilipkin/pulsemixer"
LICENSE = "MIT"

SRC_URI[sha256sum] = "11dad404a3a658d5741169194e438177061ee6e4fcb9b0ed2c9c248caacdadd5"

LIC_FILES_CHKSUM = "file://PKG-INFO;md5=a4b6e677aa40320025e5e2efd7d53f2f"

PYPI_PACKAGE = "pulsemixer"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    libpulse \
    python3-curses \
"
