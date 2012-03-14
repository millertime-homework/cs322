	.align 4
body_foo:
!locals=0, max_args=0
	save %sp,-96,%sp
! [MOVE (FIELD (PARAM 0) 0) (CONST 1)]
	mov 1,%l0
	ld [%fp+68],%l1
	st %l0,[%l1]
! [RETURN (FIELD (PARAM 0) 0)]
	ret
	restore
	ret
	restore

	.global main
	.align 4
main:
!locals=2, max_args=1
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l1]
! [MOVE (VAR 2) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-8]
! [MOVE (TEMP 2) (CALL (NAME body_foo) ( (VAR 2)))]
	ld [%fp-8],%l0
	st %l0,[%sp+68]
	call body_foo
	nop
	mov %o0,%l2
!>> Temp t2 assigned to reg %l3
	mov %o0,%l3
! [MOVE (VAR 1) (TEMP 2)]
	mov %l3,%l2
	st %l2,[%fp-4]
! [CALLST (NAME print) ( (VAR 1))]
	ld [%fp-4],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  5
!Total insts: 39
