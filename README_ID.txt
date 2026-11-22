NAD FF AIM TOOL — Android project
Desain: Premium hitam-emas

ISI:
- Slider rekomendasi sensitivitas (nilai tersimpan di perangkat)
- Panduan latihan drag headshot
- Tips HUD, DPI, dan performa
- Tidak mengubah Free Fire dan tidak memiliki aimbot/auto-fire/injector

CARA MEMBUAT APK:
1. Buka https://github.com di browser dan buat repository baru bernama NAD_FF_AIM_TOOL.
2. Upload seluruh isi folder project ZIP ini ke repository (jangan upload ZIP-nya saja; ekstrak dahulu).
3. Tambahkan file workflow berikut di .github/workflows/build.yml (buat foldernya jika belum ada):

name: Build NAD FF AIM TOOL
on: [push, workflow_dispatch]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '17'
      - uses: android-actions/setup-android@v3
      - uses: gradle/actions/setup-gradle@v4
      - name: Build APK
        run: gradle assembleDebug
      - uses: actions/upload-artifact@v4
        with:
          name: NAD-FF-AIM-TOOL-APK
          path: app/build/outputs/apk/debug/app-debug.apk

4. Buka tab Actions, pilih workflow Build NAD FF AIM TOOL, lalu jalankan Run workflow (atau tunggu build otomatis setelah push).
5. Setelah selesai, buka hasil run dan unduh artifact NAD-FF-AIM-TOOL-APK.
6. Ekstrak artifact ZIP, lalu install app-debug.apk di Android. Izinkan instalasi dari sumber tersebut jika Android meminta.

CATATAN:
- Ini source project, bukan APK yang sudah dikompilasi.
- Target Android 6.0+ (minSdk 23).
- Jika build gagal, kirim screenshot log error agar bisa dibantu.
